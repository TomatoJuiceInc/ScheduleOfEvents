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
import ru.ScheduleOfEvents.models.TemporaryTicket;
import ru.ScheduleOfEvents.repositories.PeopleRepository;
import ru.ScheduleOfEvents.sevices.BankService;
import ru.ScheduleOfEvents.sevices.PeopleService;
import ru.ScheduleOfEvents.sevices.TemporaryTicketService;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final BankService bankService;
    private final PeopleService peopleService;
    private final TemporaryTicketService ticketService;

    @Autowired
    public PaymentController(BankService bankService, PeopleService peopleService, TemporaryTicketService ticketService) {
        this.bankService = bankService;
        this.peopleService = peopleService;
        this.ticketService = ticketService;
    }

    @GetMapping()
    public String showPage(Model model,
                           @RequestParam("a") int amount,
                           @RequestParam("u") int userId,
                           @RequestParam("e") int eventId)
    {
        model.addAttribute("bankCard", new BankCard());
        model.addAttribute("amount", amount);
        model.addAttribute("user_id", userId);
        model.addAttribute("event_id", eventId);
        return "payment/show";
    }
    @GetMapping("/successful-payment")
    public String successPage()  {
        return "payment/successfulPayment";
    }


    @PostMapping("/buy/{price}/{id}/{event}")
    public String bookSeats(BankCard bankCard,
                            @PathVariable("price") int price,
                            @PathVariable("id") int id,  @PathVariable("event") int eventId) {
        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        if (bCard == null){
            System.out.println("неправильная карта");
            return "redirect:/payment?a=" + price + "&u=" + id + "&e=" + eventId;
        }
        if (bCard.getCvc().equals(bankCard.getCvc())
                && bCard.getDuration().equals(bankCard.getDuration())
                && bCard.getOwner().equals(bankCard.getOwner())){
            if (bCard.getBalance() >= price){
                ticketService.saveTickets(id, eventId);
                bankService.pay(bankCard.getCardNumber(), price);

                // todo отправить чек
                return "redirect:/payment/successful-payment" ;
            }
            System.out.println("нет денег");

            return "redirect:/payment?a=" + price + "&u=" + id  + "&e=" + eventId;
        }
        System.out.println("неправильные данные");
        return "redirect:/payment?a=" + price + "&u=" + id + "&e=" + eventId;
    }


}