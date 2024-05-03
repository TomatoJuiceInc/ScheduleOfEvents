package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Hall;
import ru.ScheduleOfEvents.sevices.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {


    private final ApplicationService applicationService;

    private final EventsService eventsService;

    private final HallsService hallsService;

    @Autowired
    public AdminController(ApplicationService applicationService, EventsService eventsService, HallsService hallsService) {
        this.applicationService = applicationService;
        this.eventsService = eventsService;
        this.hallsService = hallsService;
    }

    @GetMapping("/application")
    public String successPage(Model model)  {
        model.addAttribute("applications", applicationService.findAll());
        return "admin/application";
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable("id") int id, Model model) {
        applicationService.approveApplication(id);
        model.addAttribute("message", "Application with ID " + id + " approved successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
    }

    @PostMapping("/reject/{id}")
    public String reject(@PathVariable("id") int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
    }


    @GetMapping("/submit")
    public String submitForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("halls", hallsService.findAll());
        return "admin/submit";
    }

    @PostMapping("/submission")
    public String handleEventSubmission(Event event) {
        eventsService.save(event);
        return "redirect:/admin/successful-submission";
    }

    @GetMapping("/successful-submission")
    public String successfulSubmission() {
        return "admin/successful-submission";
    }


}











