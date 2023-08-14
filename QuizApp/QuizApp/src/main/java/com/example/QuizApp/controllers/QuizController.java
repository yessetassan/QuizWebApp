package com.example.QuizApp.controllers;

import com.example.QuizApp.models.Quiz;
import com.example.QuizApp.models.Saved_Answers;
import com.example.QuizApp.models.WhoHadCome;
import com.example.QuizApp.repositories.General_quizRepository;
import com.example.QuizApp.repositories.Quiz_repository;
import com.example.QuizApp.repositories.Saved_AnswersRepository;
import com.example.QuizApp.services.WhoHadComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("quiz")
public class QuizController {


    private final Quiz_repository quizRepository;
    private final General_quizRepository generalQuizRepository;
    private final Saved_AnswersRepository savedAnswersRepository;

    private final WhoHadComeService whoHadComeService;


    public static int get = 0, limit = 0, right_one = 0;
    public static List<Quiz> test = new ArrayList<>();
    public static List<Quiz_Saver> quiz_saver = new ArrayList<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizController(Quiz_repository quizRepository, General_quizRepository generalQuizRepository, Saved_AnswersRepository savedAnswersRepository, WhoHadComeService whoHadComeService) {
        this.quizRepository = quizRepository;
        this.generalQuizRepository = generalQuizRepository;
        this.savedAnswersRepository = savedAnswersRepository;
        this.whoHadComeService = whoHadComeService;
    }

    @GetMapping("/all")
    public String quiz_all(Model model) {
        model.addAttribute("list", generalQuizRepository.findAll());
        return "/quiz/all";
    }

    @GetMapping("/all/{id}")
    public String quiz_al_select(@PathVariable("id") Integer id, Model model) {
        test = quizRepository.take_by_id(id);
        fill_save();
        limit = test.size();
        model.addAttribute("general_quiz", generalQuizRepository.findById(id));
        model.addAttribute("quiz", test);
        return "/quiz/foa";
    }
    private void fill_save() {
        quiz_saver = new ArrayList<>();
        for (Quiz q : test) {
            System.out.println(q.toString());
            quiz_saver.add(new Quiz_Saver());
        }
    }

    @GetMapping("/all/{id}/start")
    public String quiz_start(@PathVariable("id") Integer id, Model model) {

        if (get == limit){
            get = 0;
            test = quizRepository.take_by_id(id);
            fill_save();
        }
        boolean last_one = get == limit - 1;
        String[] options = test.get(get).getAnswers().split("[|]");
        model.addAttribute("general_quiz", generalQuizRepository.findById(id));
        model.addAttribute("last_one", last_one);
        model.addAttribute("question", test.get(get).getQuestion());
        model.addAttribute("options", options);
        model.addAttribute("quiz_saver",quiz_saver.get(get));
        return "/quiz/start";
    }


    @PostMapping("/all/{id}/start")
    public String quiz_start_post(@PathVariable("id") Integer id,
                                  @ModelAttribute("quiz_saver") Quiz_Saver quizSaver){
        System.out.println(quizSaver.toString());
        quiz_saver.set(get,quizSaver);

        if (quizSaver.getAction().equals("next")) {
            get++;
        }
        else{
            if (get > 0) get--;
        }

        if (get == limit) {
            return "redirect:/quiz/all/{id}/result";
        }
        return "redirect:/quiz/all/{id}/start";
    }

    @Transactional
    @GetMapping("/all/{id}/result")
    public String result(@PathVariable("id") Integer id,  Model model) throws Exception {


        model.addAttribute("right_one",right_one);
        model.addAttribute("get",get);
        model.addAttribute("test", test);
        model.addAttribute("quiz_saver", quiz_saver);


        WhoHadCome whoHadCome = whoHadComeService.getwhoHadCome();
        int quiz_ord = quizOrder(whoHadCome.getId());
        for (int i = 0; i < limit; i++) {
            Saved_Answers savedAnswers = new Saved_Answers(id,
                    test.get(i).getId(),
                    whoHadCome.getId(),
                    quiz_saver.get(i).getAnswer(),
                    test.get(i).getRight_answer(),
                    Timestamp.valueOf(TimeString()),
                    quiz_ord);
            savedAnswersRepository.save(savedAnswers);
        }
        
        return "/quiz/result";
    }

    private String TimeString() {
        LocalDate l = LocalDate.now();
        LocalTime t = LocalTime.now();

        return l.getYear() + "-" + l.getMonthValue() + "-" + l.getDayOfMonth() + " " +
                t.getHour() + ":"+t.getMinute()+":" + t.getMinute();
    }

    private Integer quizOrder(Integer person_id) {
        List<Timestamp> timestamps = savedAnswersRepository.findByTimestamp(person_id);
        timestamps.forEach(x -> System.out.println(x.toString()));
        return timestamps.size() + 1;
    }

}

class Quiz_Saver {
    public String answer = "";
    public String action = "";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Quiz_Saver() {
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Quiz_Saver{" +
                "answer='" + answer + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
