package com.example.QuizApp.models;


import javax.persistence.*;

@Entity
@Table(name = "general_quiz")
public class General_Quiz {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public General_Quiz(String name) {
        this.name = name;
    }

    public General_Quiz() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
