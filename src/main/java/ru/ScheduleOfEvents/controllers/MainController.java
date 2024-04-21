package ru.ScheduleOfEvents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
//@RestController
//@RequestMapping("/secured")
public class MainController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/user")
    public String userAccess(Principal principal) {
        if (principal == null)
            return null;
        return principal.getName();
    }
}
