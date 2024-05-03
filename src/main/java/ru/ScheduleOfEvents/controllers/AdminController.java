package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.sevices.EventsService;
import ru.ScheduleOfEvents.sevices.HallsService;
import ru.ScheduleOfEvents.services.ApplicationService;
import ru.ScheduleOfEvents.services.EventsService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/admin")
@RequestMapping("/admin/events")
public class AdminController {

    private final EventsService eventsService;

    private final HallsService hallsService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private EventsService eventService;



    @GetMapping("/application")
    public String successPage()  {
        return "admin/application";
    public AdminController(EventsService eventsService, HallsService hallsService) {
        this.eventsService = eventsService;
        this.hallsService = hallsService;
    }

    @PostMapping("/approve")
    public String approve(@RequestParam int id, Model model) {
        applicationService.approveApplication(id);
        model.addAttribute("message", "Application with ID " + id + " approved successfully!");
        return "redirect:/admin/application";  // Порно отображаем страницу с сообщением
    @GetMapping("/past")
    public String past(Model model) {
        model.addAttribute("pastEvents", eventsService.findEventByDateBefore());
        return "admin/events/past";
    }

    @PostMapping("/reject")
    public String reject(@RequestParam int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
    }
    @GetMapping("/requests")
    public String requests() { return "admin/events/requests"; }



    @GetMapping("/events")
    public String submitApplication(Model model) {
        model.addAttribute("event", new Event());
    @GetMapping()
    public String events(Model model) {
        model.addAttribute("events", eventsService.findEventByDateAfter());
        return "admin/events";
    }

    @GetMapping("/successfully")
    public String creatingEvent(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create-event";
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("event", eventsService.findEventById(id));
        model.addAttribute("halls", hallsService.findAll());
        return "admin/events/edit";
    }


    @PostMapping("/create-event")
    public String createEvent(Event event) {
        System.out.println(1);
//        eventService.createEvent(event.getName(), event.getDate(), event.getDescription());
        System.out.println(2);
        return "redirect:/admin/successfully";
    }
}







    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("event", eventsService.findEventById(id));
        return "admin/events/show";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute("event") Event updatedEvent,
                         @RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eventDate,
                         @RequestParam("eventTime") @DateTimeFormat(pattern = "HH:mm") LocalTime eventTime) {
        updatedEvent.setDate(
                Date.from(eventDate.atTime(eventTime)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );
        eventsService.updateEvent(id, updatedEvent);
        return "redirect:/admin/events";
    }

    @PostMapping("/{id}")
    public String editEvent(@PathVariable String id) {
        return "redirect:/admin/events/{id}/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        eventsService.delete(eventsService.findEventById(id));
        return "redirect:/admin/events";
    }
}
