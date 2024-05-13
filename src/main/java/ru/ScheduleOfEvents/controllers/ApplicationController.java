package ru.ScheduleOfEvents.controllers;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.services.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;

    private final EventsService eventsService;

    private final HallsService hallsService;

    private final UserDetailsServiceImpl userDetailsService;
    private final PriceService priceService;


    @Autowired
    public ApplicationController(ApplicationService applicationService, EventsService eventsService, HallsService hallsService, UserDetailsServiceImpl userDetailsService, PriceService priceService) {
        this.applicationService = applicationService;
        this.eventsService = eventsService;
        this.hallsService = hallsService;
        this.userDetailsService = userDetailsService;
        this.priceService = priceService;
    }

    @GetMapping("/create-event")
    public String submitForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("halls", hallsService.findAll());
        return "admin/submit";
    }

    @PatchMapping("/submission")
    public String handleEventSubmission(@ModelAttribute("event") Event event,  @RequestParam("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate eventDate,
                                        @RequestParam("eventTime") @DateTimeFormat(pattern = "HH:mm") LocalTime eventTime,
                                        @RequestParam("price1") int price1, @RequestParam("price2") int price2,
                                        @RequestParam("price3") int price3, @RequestParam("price4") int price4, @RequestParam("price5") int price5) {
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
        Price priceOne = new Price(price1, event);
        Price priceTwo = new Price(price2, event);
        Price priceThree = new Price(price3, event);
        Price priceFour = new Price(price4, event);
        Price priceFive = new Price(price5, event);
        priceService.save(priceOne);
        priceService.save(priceTwo);
        priceService.save(priceThree);
        priceService.save(priceFour);
        priceService.save(priceFive);

        applicationService.save(application);
        return "redirect:/successful-submission";
    }

    @GetMapping("/successful-submission")
    public String successfulSubmission() {
        return "admin/successful-submission";
    }

    @GetMapping("/successfully")
    public String creatingEvent(Model model) {
        model.addAttribute("event", new Event());
        return "admin/create-event";}

    @PostMapping("/create-event")
    public String createEvent(Event event) {
        return "redirect:/uccessfully";
    }
}
