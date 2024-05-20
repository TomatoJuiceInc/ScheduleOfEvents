package ru.ScheduleOfEvents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
    @GetMapping()
    public String showPage() {
        return "welcome/welcome";
    }
    @PostMapping()
    public String redirectForWelcomePage(){
        return "redirect:/welcome";
    }
}
