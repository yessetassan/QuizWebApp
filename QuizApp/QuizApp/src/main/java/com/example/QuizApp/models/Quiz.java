package com.example.QuizApp.models;


import javax.persistence.*;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "general_quiz_id")
    private Integer general_quiz_id;
    @Column(name = "question")
    private String question;
    @Column(name = "answers")
    private String answers;
    @Column(name = "right_answer")
    private String right_answer;

    public Quiz(Integer general_quiz_id, String question, String answers, String right_answer) {
        this.general_quiz_id = general_quiz_id;
        this.question = question;
        this.answers = answers;
        this.right_answer = right_answer;
    }

    public Quiz() {
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getRight_answer() {
        return right_answer;
    }

    public void setRight_answer(String right_answer) {
        this.right_answer = right_answer;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", general_quiz_id=" + general_quiz_id +
                ", question='" + question + '\'' +
                ", answers='" + answers + '\'' +
                ", right_answer='" + right_answer + '\'' +
                '}';
    }
}
