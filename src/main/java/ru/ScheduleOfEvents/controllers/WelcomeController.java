package ru.ScheduleOfEvents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class WelcomeController {
    @GetMapping()
    public String showPage(){
        return "welcome/welcome";
    }
    @PostMapping("welcome")
    public String redirectForEventPage(){
        return "redirect:/events";
    }
}
