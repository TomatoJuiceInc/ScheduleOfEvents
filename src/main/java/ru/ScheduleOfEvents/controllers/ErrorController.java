package ru.ScheduleOfEvents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error1")
public class ErrorController {
    @GetMapping()
    public String errorPage(){
        System.out.println("Sdf");
        return "error/error";
    }
}
