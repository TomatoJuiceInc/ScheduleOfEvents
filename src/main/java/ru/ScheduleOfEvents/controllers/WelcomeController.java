package ru.ScheduleOfEvents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WelcomeController {
    @GetMapping()
    public String showPage() {
        return "welcome/welcome";
    }

    @PostMapping()
    public String redirectForWelcomePage() {
        return "redirect:/";
    }

    @GetMapping("/about")
    public String aboutUsPage() {
        return "aboutUs/aboutUs";
    }
}
