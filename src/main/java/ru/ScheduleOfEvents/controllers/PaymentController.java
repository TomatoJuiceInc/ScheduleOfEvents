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

    @Autowired
    public PaymentController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping()
    public String showPage(Model model, @RequestParam("a") int amount)  {
        model.addAttribute("bankCard", new BankCard());
        model.addAttribute("amount", amount);
        return "payment/show";
    }

    @PostMapping("/buy/{price}")
    public String bookSeats(BankCard bankCard, @PathVariable("price") int price) {
        System.out.println(price);
        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        if (bCard == null){
            System.out.println("не правильная карта");
            return "redirect:/payment?a=" + price;
        }
        System.out.println(234234);
        if (bCard.getCvc().equals(bankCard.getCvc())
                && bCard.getDuration().equals(bankCard.getDuration())
                && bCard.getOwner().equals(bankCard.getOwner())){
            if (bCard.getBalance() >= price){
                System.out.println("снимаем деньги");
                bCard.setBalance(bankCard.getBalance() - price);
                return "redirect:/order?type=2" ;
            }
            System.out.println("нет денег");

            return "redirect:/payment?a=" + price;
        }
        System.out.println("неправильные данные");
        return "redirect:/payment?a=" + price;
    }


}