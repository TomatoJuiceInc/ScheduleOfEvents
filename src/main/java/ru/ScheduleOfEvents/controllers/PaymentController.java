package ru.ScheduleOfEvents.controllers;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.MailStructure;
import ru.ScheduleOfEvents.services.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final BankService bankService;
    private final UserDetailsServiceImpl userDetailsService;
    private final TemporaryTicketService ticketService;
    private final MailService mailService;
    private final EventsService eventsService;

    @Autowired
    public PaymentController(BankService bankService, UserDetailsServiceImpl userDetailsService, TemporaryTicketService ticketService, MailService mailService, EventsService eventsService) {
        this.bankService = bankService;
        this.userDetailsService = userDetailsService;
        this.ticketService = ticketService;
        this.mailService = mailService;
        this.eventsService = eventsService;
    }

    @GetMapping()
    public String showPage(Model model,
                           @RequestParam("a") int amount,
                           @RequestParam("u") int userId,
                           @RequestParam("e") int eventId,
                           @RequestParam(value = "error", required = false) String  isError)
    {
        model.addAttribute("bankCard", new BankCard());
        model.addAttribute("amount", amount);
        model.addAttribute("user_id", userId);
        model.addAttribute("event_id", eventId);
        if (isError != null){
            model.addAttribute("error", true);
        }
        else {
            model.addAttribute("error", false);
        }

        return "payment/show";
    }
    @GetMapping("/successful-payment")
    public String successPage()  {
        return "succes/successfulPayment";
    }


    @PostMapping("/buy/{price}/{id}/{event}")
    public String buy(BankCard bankCard,
                            @PathVariable("price") int price,
                            @PathVariable("id") int id,  @PathVariable("event") int eventId) {

        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        System.out.println(bCard.getCardNumber());
        if (bCard == null){
            return String.format("redirect:/payment?a=%d&u=%d&e=%d&error=%b", price, id, eventId, true);
        }
        if (bCard.getCvc().equals(bankCard.getCvc())
                && bCard.getDuration().equals(bankCard.getDuration())
                && bCard.getOwner().equals(bankCard.getOwner())){
            if (bCard.getBalance() >= price){


                //
                Event event = eventsService.findOne(eventId);
                MailStructure mailStructure = new MailStructure();
                mailStructure.setPrice(price);
                mailStructure.setPlace(event.getHall().getName());
                mailStructure.setEventTimeAndName(LocalDateTime.ofInstant(event.getDate().toInstant(), ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                        + " " + event.getName());
                mailStructure.setTimeOfPurchase(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                mailStructure.setSubject("SheduleOfEvents");
                mailStructure.setTickets(ticketService.findAllById(id, eventId));
                ticketService.saveTickets(id, eventId);
                bankService.pay(bankCard.getCardNumber(), price);
                try {
                    mailService.sendMail( "khairullov.ruslan2405@yandex.ru", mailStructure);
                }
                catch (MessagingException e){
                    System.out.println("pu pu pu");
                }
                return "redirect:/payment/successful-payment" ;
            }
        }
        return String.format("redirect:/payment?a=%d&u=%d&e=%d&error=%b", price, id, eventId, true);
    }


}