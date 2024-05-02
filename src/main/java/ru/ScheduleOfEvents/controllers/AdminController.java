package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.services.ApplicationService;
import ru.ScheduleOfEvents.services.EventsService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private EventsService eventService;



    @GetMapping("/application")
    public String successPage()  {
        return "admin/application";
    }

    @PostMapping("/approve")
    public String approve(@RequestParam int id, Model model) {
        applicationService.approveApplication(id);
        model.addAttribute("message", "Application with ID " + id + " approved successfully!");
        return "redirect:/admin/application";  // Порно отображаем страницу с сообщением
    }

    @PostMapping("/reject")
    public String reject(@RequestParam int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
    }



    @GetMapping("/events")
    public String submitApplication(Model model) {
        model.addAttribute("event", new Event());
        return "admin/events";
    }

    @GetMapping("/successfully")
    public String creatingEvent(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create-event";
    }


    @PostMapping("/create-event")
    public String createEvent(Event event) {
        System.out.println(1);
//        eventService.createEvent(event.getName(), event.getDate(), event.getDescription());
        System.out.println(2);
        return "redirect:/admin/successfully";
    }
}











