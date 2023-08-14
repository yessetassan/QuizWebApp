package com.example.QuizApp.auth;


import com.example.QuizApp.models.Person;
import com.example.QuizApp.models.Saved_Answers;
import com.example.QuizApp.repositories.PersonRepository;
import com.example.QuizApp.services.WhoHadComeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final WhoHadComeService whoHadComeService;



    @Autowired
    public PersonDetailsService(PersonRepository personRepository, WhoHadComeService whoHadComeService) {
        this.personRepository = personRepository;
        this.whoHadComeService = whoHadComeService;
    }

    public Optional<Person> findBYId(Integer id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("Person with name doesn't exist !");

        whoHadComeService.save(person.get());
        return new PersonDetails(person.get());
    }
    @Transactional
    public void save(Person person) {
        person.setPassword(passwordEncoder().encode(person.getPassword()));
        personRepository.save(person);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
