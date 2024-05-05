package ru.ScheduleOfEvents.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.services.HallsService;
import ru.ScheduleOfEvents.services.ApplicationService;
import ru.ScheduleOfEvents.models.Hall;
import ru.ScheduleOfEvents.sevices.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/events")
public class AdminController {


    private final ApplicationService applicationService;

    private final EventsService eventsService;

    private final HallsService hallsService;
    private final EventsService eventsService;

    private final HallsService hallsService;

    @Autowired
    public AdminController(ApplicationService applicationService, EventsService eventsService, HallsService hallsService) {
        this.applicationService = applicationService;
        this.eventsService = eventsService;
        this.hallsService = hallsService;
    }
    private final  ApplicationService applicationService;




    @GetMapping("/application")
    public String successPage(Model model)  {
        model.addAttribute("applications", applicationService.findAll());
        return "admin/application";
    public String successPage()  {
        return "admin/application";}

    public AdminController(EventsService eventsService, HallsService hallsService, ApplicationService applicationService) {
        this.eventsService = eventsService;
        this.hallsService = hallsService;
        this.applicationService = applicationService;
    }

    @PostMapping("/approve/{id}")
    public String approve(@PathVariable("id") int id, Model model) {
        applicationService.approveApplication(id);
        model.addAttribute("message", "Application with ID " + id + " approved successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
        return "redirect:/admin/application";
    }
    @GetMapping("/past")
    public String past(Model model) {
        model.addAttribute("pastEvents", eventsService.findEventByDateBefore());
        return "admin/events/past";
    }

    @PostMapping("/reject/{id}")
    public String reject(@PathVariable("id") int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/application";  // Повторно отображаем страницу с сообщением
    }
    @GetMapping("/requests")
    public String requests() { return "admin/events/requests"; }


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

    @GetMapping()
    public String events(Model model) {
        model.addAttribute("events", eventsService.findEventByDateAfter());
        return "admin/events/events";
    }

    @GetMapping("/successful-submission")
    public String successfulSubmission() {
        return "admin/successful-submission";
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


}
    @PostMapping("/create-event")
    public String createEvent(Event event) {
        System.out.println(1);
//        eventService.createEvent(event.getName(), event.getDate(), event.getDescription());
        System.out.println(2);
        return "redirect:/admin/successfully";
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
