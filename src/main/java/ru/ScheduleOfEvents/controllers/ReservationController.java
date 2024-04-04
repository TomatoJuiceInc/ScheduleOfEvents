package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.Event;
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

    @GetMapping()
    public String showPage(Model model, @RequestParam("type") int type){
        ObjectMapper mapper = new ObjectMapper();

        // Создайте JSON объект
        String jsonReservedSeats = "";
        String jsonPriceSeats = "";
        Event event = new Event("Кунг-Фу Панда 4 ", new Date(),
                "Hello!This is test event, pu-pu-pu");
        LocalDateTime dateTime = LocalDateTime.ofInstant(event.getDate().toInstant(), ZoneId.of("Europe/Moscow"));
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        try {
            jsonReservedSeats = mapper.writeValueAsString(List.of("26 1", "26 2"));
            jsonPriceSeats = mapper.writeValueAsString(List.of("1000", "1200", "1300", "1500", "1600"));;
        } catch (Exception e) {
            // Обработка ошибки сериализации в JSON
            e.printStackTrace();

            // todo сделать другим цветом
        }
        model.addAttribute("seatData", new SeatData());
        model.addAttribute("event", event);
        model.addAttribute("reservedSeats", jsonReservedSeats);
        model.addAttribute("priceSeats", jsonPriceSeats);
        model.addAttribute("time", formattedDateTime);
        model.addAttribute("priceSeatsForHtml", List.of("1000", "1200", "1300", "1500", "1600"));
        model.addAttribute("typeHall", type);
        return "order/show";
    }

    @PostMapping("/more")
    public String bookSeats(@ModelAttribute("seatData") SeatData seatData ) {
        System.out.println(Arrays.toString(seatData.getSeat().split(",")));


        return "redirect:/order";
    }


}

