package com.example.QuizApp.services;

import com.example.QuizApp.models.Person;
import com.example.QuizApp.models.WhoHadCome;
import com.example.QuizApp.repositories.WhoHadComeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WhoHadComeService {

    private final WhoHadComeRepository whoHadComeRepository;

    @Autowired
    public WhoHadComeService(com.example.QuizApp.repositories.WhoHadComeRepository whoHadComeRepository) {
        this.whoHadComeRepository = whoHadComeRepository;
    }

    public WhoHadCome getwhoHadCome() {
        return all().get(0);
    }

    public List<WhoHadCome> all() {
        return whoHadComeRepository.findAll();
    }
    @Transactional
    public void truncate() {
        whoHadComeRepository.deleteAll();
    }

    @Transactional
    public void save(Person person) {
        WhoHadCome whoHadCome = new WhoHadCome();
        whoHadCome.setId(person.getId());
        System.out.println(whoHadCome);
        whoHadComeRepository.save(whoHadCome);
    }

}
