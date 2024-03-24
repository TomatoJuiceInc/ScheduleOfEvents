package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.util.SeatData;

import java.util.*;

@Controller
@RequestMapping("/order")

// todo нароисовать номера рядов, закрыть доступ уже забронированным местам
public class ReservationController {

    @GetMapping()
    public String showPage(Model model){
        ObjectMapper mapper = new ObjectMapper();

        // Создайте JSON объект
        String jsonReservedSeats = "";
        String jsonPriceSeats = "";

        try {
            jsonReservedSeats = mapper.writeValueAsString(List.of("26 1", "26 2"));
            jsonPriceSeats = mapper.writeValueAsString(List.of("1000", "1200", "1300", "1500", "1600"));;
        } catch (Exception e) {
            // Обработка ошибки сериализации в JSON
            e.printStackTrace();
        }
        model.addAttribute("seatData", new SeatData());
        model.addAttribute("reservedSeats", jsonReservedSeats);
        model.addAttribute("priceSeats", jsonPriceSeats);
        model.addAttribute("priceSeatsForHtml", List.of("1000", "1200", "1300", "1500", "1600"));
        return "order/show";
    }

    @PostMapping("/more")
    public String bookSeats(@ModelAttribute("seatData") SeatData seatData ) {
        System.out.println(Arrays.toString(seatData.getSeat().split(",")));


        return "redirect:/order";
    }


}

