package com.example.QuizApp.models;

import javax.persistence.*;

@Entity
@Table(name = "whohadcome")
public class WhoHadCome {

    @Id
    @Column(name = "id")
    private Integer id;

    public WhoHadCome(Integer id) {
        this.id = id;
    }

    public WhoHadCome() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WhoHadCome{" +
                "id=" + id +
                '}';
    }
}
