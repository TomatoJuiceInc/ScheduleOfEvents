package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.sevices.EventsService;
import ru.ScheduleOfEvents.sevices.HallsService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
@RequestMapping("/admin/events")
public class AdminController {

    private final EventsService eventsService;

    private final HallsService hallsService;

    @Autowired
    public AdminController(EventsService eventsService, HallsService hallsService) {
        this.eventsService = eventsService;
        this.hallsService = hallsService;
    }

    @GetMapping("/past")
    public String past(Model model) {
        model.addAttribute("pastEvents", eventsService.findEventByDateBefore());
        return "admin/events/past";
    }

    @GetMapping("/requests")
    public String requests() { return "admin/events/requests"; }


    @GetMapping()
    public String events(Model model) {
        model.addAttribute("events", eventsService.findEventByDateAfter());
        return "admin/events";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("event", eventsService.findEventById(id));
        model.addAttribute("halls", hallsService.findAll());
        return "admin/events/edit";
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
