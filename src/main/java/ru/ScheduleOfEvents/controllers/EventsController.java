package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.sevices.EventsService;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Controller
@RequestMapping()
public class EventsController {
    private final EventsService eventsService;
    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }
    @GetMapping
    public String showWelcomePage(){
        return "base";
    }
    @GetMapping("/events")
    public String showEvents(@RequestParam(value = "firstParam",required = false,defaultValue = "defaultFirst") String firstParam, Model model) {

        model.addAttribute("event", eventsService.findAll().stream().sorted(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                int i = switch (firstParam) {
                    case "age" -> o2.compareAge(o1.getAge());
                    case "date" -> o1.getDate().compareTo(o2.getDate());
                    case "ageReverse" -> o1.compareAge(o2.getAge());
                    default -> o2.getDate().compareTo(o1.getDate());
                };
                return i;
            }
        }).toList());
        return "Events";
    }
}
