package com.example.QuizApp.controllers;

import com.example.QuizApp.auth.PersonDetailsService;
import com.example.QuizApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping
public class WelcomeController {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public WelcomeController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping( "/welcome")
    public String welcome() {
        return "/welcome";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("person")Person person) {
        return "/login";
    }



    @GetMapping("/registration")
    public String registration(@ModelAttribute("person")Person person) {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration_post(@ModelAttribute("person")Person person) {
        System.out.println(person);
        personDetailsService.save(person);
        return "/login";
    }




}
