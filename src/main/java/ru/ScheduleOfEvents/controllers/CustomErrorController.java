package ru.ScheduleOfEvents.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String errorPage(){
        System.out.println("Sdf");
        return "error/error";
    }
}
