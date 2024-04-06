package ru.ScheduleOfEvents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class BooksController {
    @GetMapping
    public String test(){
        return "base.html";
    }
    @GetMapping("/events")
    public String test2(){
        return "Events.html";
    }
}
