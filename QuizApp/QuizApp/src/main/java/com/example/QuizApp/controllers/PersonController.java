package com.example.QuizApp.controllers;

import com.example.QuizApp.auth.PersonDetailsService;
import com.example.QuizApp.models.Quiz;
import com.example.QuizApp.models.Saved_Answers;
import com.example.QuizApp.repositories.Quiz_repository;
import com.example.QuizApp.repositories.Saved_AnswersRepository;
import com.example.QuizApp.services.WhoHadComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonDetailsService personDetailsService;
    private final WhoHadComeService whoHadComeService;

    private final Quiz_repository quizRepository;

    private final Saved_AnswersRepository savedAnswersRepository;

    private static Map<Integer, List<Saved_Answers>> map = new HashMap<>();


    @Autowired
    public PersonController(PersonDetailsService personDetailsService, WhoHadComeService whoHadComeService, Quiz_repository quizRepository, Saved_AnswersRepository savedAnswersRepository) {
        this.personDetailsService = personDetailsService;
        this.whoHadComeService = whoHadComeService;
        this.quizRepository = quizRepository;
        this.savedAnswersRepository = savedAnswersRepository;
    }

    @GetMapping
    public String personPage(Model model) {
        int person_id = whoHadComeService.getwhoHadCome().getId();

        model.addAttribute("person" , personDetailsService.findBYId(person_id).get());

        List<Saved_Answers> items = savedAnswersRepository.findAllByPerson_id(person_id);
        map = new HashMap<>();
        for (Saved_Answers item : items) {
            int ord = item.getQuiz_order();
            map.putIfAbsent(ord, new ArrayList<>());
            map.get(ord).add(item);
        }

        model.addAttribute("map", map);
        return "person/page";
    }

    @GetMapping("/{quiz_order}")
    public String show_Quiz(@PathVariable("quiz_order") Integer quiz_order, Model model) {

        List<Saved_Answers> list = map.get(quiz_order);
        List<Quiz_Time> quizTimes = new ArrayList<>();

        for (Saved_Answers l : list) {
            Quiz quiz = index(l.getGeneral_quiz_id(),l.getQuiz_id());
            quizTimes.add(new Quiz_Time(quiz.getQuestion(), l.getAnswer(), l.getRightAnswer()));
        }
        model.addAttribute("quiz", quizTimes);

        return "person/result";
    }

    public Quiz index(Integer general_quiz_id, Integer quiz_id) {
        return quizRepository.findByGeneral_quiz_idAndId(quiz_id, general_quiz_id);
    }

    @GetMapping("/logout")
    public String logout() {
        whoHadComeService.truncate();
        return "person/logout";
    }

    @PostMapping("/logout")
    public String logout2() {
        return "redirect:/logout";
    }


}

class Quiz_Time {
    private String question;
    private String answer;
    private String rightAnswer;

    public Quiz_Time(String question, String answer, String rightAnswer) {
        this.question = question;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
    }

    public Quiz_Time() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
