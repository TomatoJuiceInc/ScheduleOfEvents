package ru.ScheduleOfEvents.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.ScheduleOfEvents.models.Application;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.services.HallsService;
import ru.ScheduleOfEvents.services.ApplicationService;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@Controller
@RequestMapping("/admin/events")
public class AdminController {


    private final ApplicationService applicationService;

    private final EventsService eventsService;

    private final HallsService hallsService;
    private final UserDetailsServiceImpl userDetailsService;


    @Autowired
    public AdminController(ApplicationService applicationService, EventsService eventsService, HallsService hallsService, UserDetailsServiceImpl userDetailsService) {
        this.applicationService = applicationService;
        this.eventsService = eventsService;
        this.hallsService = hallsService;
        this.userDetailsService = userDetailsService;
    }





    // checked
    @GetMapping("/application")
    public String successPage(Model model)  {
        model.addAttribute("applications", applicationService.findAll());
        return "admin/application";
    }



    // checked
    @PostMapping("/approve/{id}")
    public String approve(@PathVariable("id") int id, Model model) {
        applicationService.approveApplication(id);
        model.addAttribute("message", "Application with ID " + id + " approved successfully!");
        return "redirect:/admin/events/application";  // Повторно отображаем страницу с сообщением
    }
    // checked
    @GetMapping("/past")
    public String past(Model model) {
        model.addAttribute("pastEvents", eventsService.findEventByDateBefore());
        return "admin/events/past";
    }

    // checked

    @PostMapping("/reject/{id}")
    public String reject(@PathVariable("id") int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/events/application";  // Повторно отображаем страницу с сообщением
    }

    @GetMapping("/requests")
    public String requests() { return "admin/events/requests"; }


    @GetMapping("/submit")
    public String submitForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("halls", hallsService.findAll());
        return "admin/submit";
    }

    @PatchMapping("/submission")
    public String handleEventSubmission( Event event, @RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eventDate,
                                         @RequestParam("eventTime") @DateTimeFormat(pattern = "HH:mm") LocalTime eventTime) {
        event.setDate(
                Date.from(eventDate.atTime(eventTime)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );
        event.setStatus(false);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        Application application = new Application(user, event);
        application.setIsApproved(false);
        event.setOwner(user);
        eventsService.save(event);
        applicationService.save(application);
        return "redirect:/admin/events/successful-submission";
    }

    @GetMapping()
    public String events(Model model) {
        model.addAttribute("events", eventsService.findEventByDateAfter());
        return "admin/events/events";
    }

    @GetMapping("/successful-submission")
    public String successfulSubmission() {
        return "admin/successful-submission";
    }
    @GetMapping("/successfully")
    public String creatingEvent(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create-event";}

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("event", eventsService.findEventById(id));
        model.addAttribute("halls", hallsService.findAll());

        return "admin/events/edit";
    }



    @PostMapping("/create-event")
    public String createEvent(Event event) {
        return "redirect:/admin/events/successfully";
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
