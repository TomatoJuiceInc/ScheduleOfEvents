package ru.ScheduleOfEvents.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Price;
import ru.ScheduleOfEvents.sevices.BankService;
import ru.ScheduleOfEvents.sevices.PeopleService;
import ru.ScheduleOfEvents.util.SeatData;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final BankService bankService;
    private final PeopleService peopleService;

    @Autowired
    public PaymentController(BankService bankService, PeopleService peopleService) {
        this.bankService = bankService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showPage(Model model, @RequestParam("a") int amount, @RequestParam("u") int user_id,  @RequestParam("s") String seats)
    {
        System.out.println(seats);
        model.addAttribute("bankCard", new BankCard());
        model.addAttribute("amount", amount);
        model.addAttribute("user_id", user_id);
        model.addAttribute("seats", seats);
        return "payment/show";
    }
    @GetMapping("/successful-payment")
    public String successPage()  {
        return "payment/successfulPayment";
    }


    @PostMapping("/buy/{price}/{id}/{s}")
    public String bookSeats(BankCard bankCard, String seats,
                            @PathVariable("price") int price,
                            @PathVariable("id") int id,
                            @PathVariable("s") String s) {
        System.out.println("user_id " + id );
        System.out.println(seats);
//
        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        if (bCard == null){
            System.out.println("неправильная карта");
            return "redirect:/payment?a=" + price;
        }
        System.out.println(234234);
        if (bCard.getCvc().equals(bankCard.getCvc())
                && bCard.getDuration().equals(bankCard.getDuration())
                && bCard.getOwner().equals(bankCard.getOwner())){
            if (bCard.getBalance() >= price){
                System.out.println("снимаем деньги");
                bankService.pay(bankCard.getCardNumber(), price);
                // todo отправить чек
                return "redirect:/payment/successful-payment" ;
            }
            System.out.println("нет денег");

            return "redirect:/payment?a=" + price + "&u=" + id ;
        }
        System.out.println("неправильные данные");
        return "redirect:/payment?a=" + price + "&u=" + id ;
    }


}