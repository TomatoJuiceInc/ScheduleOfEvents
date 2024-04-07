package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Price;
import ru.ScheduleOfEvents.sevices.EventsService;
import ru.ScheduleOfEvents.util.SeatData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/order")

// todo нароисовать номера рядов, закрыть доступ уже забронированным местам
public class ReservationController {
    private final EventsService eventsService;

    @Autowired
    public ReservationController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping()
    public String showPage(Model model, @RequestParam("type") int type,  @RequestParam("e") int eventId)  {
        ObjectMapper mapper = new ObjectMapper();

        // Создайте JSON объект
        String jsonReservedSeats = "";
        String jsonPriceSeats = "";
        Event event = eventsService.findOne(eventId);
        if (event == null){
            return "error/error.html";
        }

        LocalDateTime dateTime = LocalDateTime.ofInstant(event.getDate().toInstant(), ZoneId.of("Europe/Moscow"));
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        try {
            jsonReservedSeats = mapper.writeValueAsString(
                    event.getTickets()
                            .stream()
                            .map(t -> (t.getCol() + " " + t.getRow()))
                            .toList());

            jsonPriceSeats = mapper.writeValueAsString(event.getPrices()
                    .stream()
                    .map(Price::getPrice)
                    .toList().stream().sorted(Integer::compareTo).toList());
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }


        model.addAttribute("seatData", new SeatData());
        model.addAttribute("event", event);
        model.addAttribute("reservedSeats", jsonReservedSeats);
        model.addAttribute("priceSeats", jsonPriceSeats);
        model.addAttribute("time", formattedDateTime);
        model.addAttribute("priceSeatsForHtml", event.getPrices()
                .stream()
                .map(Price::getPrice)
                .toList().stream().sorted(Integer::compareTo).toList());
        model.addAttribute("typeHall", type);
        return "order/show";
    }

    @PostMapping("/more")
    public String bookSeats(@ModelAttribute("seatData") SeatData seatData ) {
        System.out.println(Arrays.toString(seatData.getSeat().split(",")));


        return "redirect:/order";
    }


}

