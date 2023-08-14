package com.example.QuizApp.models;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "saves_answers")
public class Saved_Answers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "general_quiz_id")
    private Integer general_quiz_id;
    @Column(name = "quiz_id")
    private Integer quiz_id;
    @Column(name = "person_id")
    private Integer person_id;
    @Column(name = "answer")
    private String answer;
    @Column(name = "isrightanswer")
    private String isRightAnswer;
    @Column(name = "posted_date")
    private Timestamp posted_date;

    @Column(name = "quiz_order")
    private Integer quiz_order;

    public Saved_Answers(Integer general_quiz_id, Integer quiz_id, Integer person_id, String answer, String isRightAnswer, Timestamp posted_date, Integer quizOrder) {
        this.general_quiz_id = general_quiz_id;
        this.quiz_id = quiz_id;
        this.person_id = person_id;
        this.answer = answer;
        this.isRightAnswer = isRightAnswer;
        this.posted_date = posted_date;
        this.quiz_order = quizOrder;
    }

    public Saved_Answers() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGeneral_quiz_id() {
        return general_quiz_id;
    }

    public void setGeneral_quiz_id(Integer general_quiz_id) {
        this.general_quiz_id = general_quiz_id;
    }

    public Integer getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Integer quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRightAnswer() {
        return isRightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        isRightAnswer = rightAnswer;
    }

    public Timestamp getPosted_date() {
        return posted_date;
    }

    public void setPosted_date(Timestamp posted_date) {
        this.posted_date = posted_date;
    }

    @Override
    public String toString() {
        return "Saved_Answers{" +
                "id=" + id +
                ", general_quiz_id=" + general_quiz_id +
                ", quiz_id=" + quiz_id +
                ", person_id=" + person_id +
                ", answer='" + answer + '\'' +
                ", isRightAnswer='" + isRightAnswer + '\'' +
                ", posted_date=" + posted_date +
                '}';
    }

    public Integer getQuiz_order() {
        return quiz_order;
    }

    public void setQuiz_order(Integer quiz_order) {
        this.quiz_order = quiz_order;
    }
}
