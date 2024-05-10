package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationFailureProxyUntrustedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Price;
import ru.ScheduleOfEvents.models.TemporaryTicket;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.services.EventsService;
import ru.ScheduleOfEvents.services.PriceService;
import ru.ScheduleOfEvents.services.TemporaryTicketService;
import ru.ScheduleOfEvents.services.UserDetailsServiceImpl;
import ru.ScheduleOfEvents.util.SeatData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
@RequestMapping("/order")

public class ReservationController {
    private final EventsService eventsService;

    @Autowired
    public ReservationController(EventsService eventsService, UserDetailsServiceImpl userDetailsService, TemporaryTicketService temporaryTicketService, PriceService priceService) {
        this.eventsService = eventsService;
    }

    @GetMapping()
    public String showPage(Model model, @RequestParam("type") int type,
                                        @RequestParam("e") int eventId)  {
        ObjectMapper mapper = new ObjectMapper();
        String jsonReservedSeats = "";
        String jsonPriceSeats = "";
        String jsonPriceId = "";
        Event event = eventsService.findOne(eventId);
        if (event == null){
            return "error/error.html";
        }

        LocalDateTime dateTime = LocalDateTime.ofInstant(event.getDate().toInstant(), ZoneId.of("Europe/Moscow"));
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        Set<String> reserved =new HashSet<>(event.getTemporaryTickets()
                .stream()
                .map(t -> (t.getCol() + " " + t.getRow()))
                .toList());
        Set<String> purchased = new HashSet<>(event.getTickets()
                .stream()
                .map(t -> (t.getCol() + " " + t.getRow()))
                .toList());
        reserved.addAll(purchased);
        try {
            jsonReservedSeats = mapper.writeValueAsString(reserved);

            jsonPriceSeats = mapper.writeValueAsString(event.getPrices()
                    .stream()
                    .map(Price::getPrice)
                    .toList().stream().sorted(Integer::compareTo).toList());
            jsonPriceId = mapper.writeValueAsString(event.getPrices()
                    .stream().sorted((Comparator.comparingInt(Price::getPrice))).map(Price::getId).toList());
            System.out.println(jsonPriceId);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }


        model.addAttribute("seatData", new SeatData());
        model.addAttribute("event", event);
        model.addAttribute("reservedSeats", jsonReservedSeats);
        model.addAttribute("priceSeats", jsonPriceSeats);
        model.addAttribute("priceId", jsonPriceId);
        model.addAttribute("time", formattedDateTime);
        model.addAttribute("priceSeatsForHtml", event.getPrices()
                .stream()
                .map(Price::getPrice)
                .toList().stream().sorted(Integer::compareTo).toList());
        model.addAttribute("typeHall", type);
        return "order/show";
    }

    @PostMapping("/more/{event}")
    public String bookSeats(@ModelAttribute("seatData") SeatData seatData,
                            @PathVariable("event") int event ) {




        if (seatData == null){
            return "error/error.html";
        }


        return String.format("redirect:/payment?e=%d&seats=%s", event, seatData.getSeat());

    }




}

