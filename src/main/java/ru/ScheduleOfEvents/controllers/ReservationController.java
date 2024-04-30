package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserDetailsServiceImpl userDetailsService;
    private final TemporaryTicketService temporaryTicketService;
    private final PriceService priceService;

    @Autowired
    public ReservationController(EventsService eventsService, UserDetailsServiceImpl userDetailsService, TemporaryTicketService temporaryTicketService, PriceService priceService) {
        this.eventsService = eventsService;
        this.userDetailsService = userDetailsService;
        this.temporaryTicketService = temporaryTicketService;
        this.priceService = priceService;
    }

    @GetMapping()
    public String showPage(Model model, @RequestParam("type") int type,
                                        @RequestParam("e") int eventId,
                                        @RequestParam("u") int userId)  {
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
        model.addAttribute("userId", userId);
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

    @PostMapping("/more/{id}/{event}")
    public String bookSeats(@ModelAttribute("seatData") SeatData seatData,
                            @PathVariable("id") int id,
                            @PathVariable("event") int event ) {
        if (seatData == null){
            return "error/error.html";
        }
        List<TemporaryTicket> temporaryTickets = temporaryTicketService.findAll();
        for (TemporaryTicket temporaryTicket: temporaryTickets){
            if (checkTempTicket(temporaryTicket)){
                temporaryTicketService.deleteById(temporaryTicket.getId());
            }
        }

        Date currentDate = new Date();
        User user = userDetailsService.findOne(id);
        Event eventDB = eventsService.findOne(id);
        int price = 0;
        for(String seat: seatData.getSeat().split(",")){
            String[] currentSeat = seat.split(":");
            Price price1 = priceService.findOne(Integer.parseInt(currentSeat[1]));
            price += price1.getPrice();
            TemporaryTicket temporaryTicket =  new TemporaryTicket(
                    currentSeat[0].split(" ")[0],
                    currentSeat[0].split(" ")[1],
                    currentDate ,
                    eventsService.findOne(event),
                    userDetailsService.findOne(id),
                    price1);

            temporaryTicket.setOwnerEventForTT(eventDB);
            temporaryTicket.setOwnerUserForTT(user);
            user.getTemporaryTickets().add(temporaryTicket);
            eventDB.getTemporaryTickets().add(temporaryTicket);
            temporaryTicketService.save(temporaryTicket);
        }
        userDetailsService.save(user);
        eventsService.save(eventDB);
        return String.format("redirect:/payment?a=%d&u=%d&e=%d", price, id, event);

    }

    public boolean checkTempTicket(TemporaryTicket temporaryTicket){
        Date now = new Date();
        return  (now.getTime() - temporaryTicket.getTime().getTime()) / 60000 >= 10;
    }


}

