package ru.ScheduleOfEvents.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.services.ApplicationService;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.services.HallsService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin/events")
public class AdminController {
    private final ApplicationService applicationService;
    private final EventsService eventsService;
    private final HallsService hallsService;

    @GetMapping()
    public String events(Model model, @RequestParam(value = "choice", required = false) String choice) {
        if (choice == null) {
            model.addAttribute("events", eventsService.findEventByDateAfter());
        } else {
            switch (choice) {
                case "past":
                    model.addAttribute("events", eventsService.findEventByDateBefore());
                    break;
                case "current":
                    model.addAttribute("events", eventsService.findEventByDateAfter());
                    break;
            }
        }
        return "admin/events/events";
    }

    // checked
    @GetMapping("/application")
    public String successPage(Model model) {
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
    /*// checked
    @GetMapping("/past")
    public String past(Model model) {
        model.addAttribute("pastEvents", eventsService.findEventByDateBefore());
        return "admin/events/past";
    }
*/
    // checked

    @PostMapping("/reject/{id}")
    public String reject(@PathVariable("id") int id, Model model) {
        applicationService.rejectApplication(id);
        model.addAttribute("message", "Application with ID " + id + " rejected successfully!");
        return "redirect:/admin/events/application";
    }

    @GetMapping("/requests")
    public String requests() {
        return "admin/events/requests";
    }

    /*@GetMapping()
    public String events(Model model) {
        model.addAttribute("events", eventsService.findEventByDateAfter());
        return "admin/events/events";
    }*/

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Event event = eventsService.findEventById(id);
        System.out.println(event.getHall().getName());
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
