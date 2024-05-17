package ru.ScheduleOfEvents.controllers;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.services.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    private final BankService bankService;
    private final UserDetailsServiceImpl userDetailsService;
    private final TicketService ticketService;
    private final MailService mailService;
    private final EventsService eventsService;
    private final PriceService priceService;

    @Autowired
    public PaymentController(BankService bankService, UserDetailsServiceImpl userDetailsService, TicketService ticketService, MailService mailService, EventsService eventsService, PriceService priceService) {
        this.bankService = bankService;
        this.userDetailsService = userDetailsService;
        this.ticketService = ticketService;
        this.mailService = mailService;
        this.eventsService = eventsService;
        this.priceService = priceService;
    }

    @GetMapping()
    public String showPage(Model model,
                           @RequestParam("seats") String seats,
                           @RequestParam("e") int eventId,
                           @RequestParam(value = "error", required = false) String  isError,
                           @RequestParam(value = "message", required = false, defaultValue = " Введены неправильные данные, попробуйте еще раз!") String  message)
    {
        int price = 0;

        for(String seat: seats.split(",")){
            String[] currentSeat = seat.split(":");
            Price price1 = priceService.findOne(Integer.parseInt(currentSeat[1]));
            price += price1.getPrice();
        }
        model.addAttribute("bankCard", new BankCard());
        model.addAttribute("amount", price);
        model.addAttribute("event_id", eventId);
        model.addAttribute("seats", seats);

        model.addAttribute("message", message);
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


    @PostMapping("/buy/{price}/{event}/{seats}")
    public String buy(BankCard bankCard,
                            @PathVariable("price") int price, @PathVariable("event") int eventId,
                      @PathVariable("seats") String seats) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String name = userDetails.getUsername();
        User user = userDetailsService.findByUsername(name);
        Long id = user.getId();
        Event eventDB = eventsService.findOne(eventId);
        if (bankCard == null){
            return String.format("redirect:/payment?e=%d&seats=%s&error=%b", eventId, seats, true);
        }


        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        if (bCard == null){
            return String.format("redirect:/payment?e=%d&seats=%s&error=%b", eventId, seats, true);
        }
        if (bCard.getCvc().equals(bankCard.getCvc())
                && bCard.getDuration().equals(bankCard.getDuration())
                && bCard.getOwner().equals(bankCard.getOwner())){
            if (bCard.getBalance() >= price){
                List<Ticket> tickets = new ArrayList<>();
                for(String seat: seats.split(",")){
                    String[] currentSeat = seat.split(":");
                    Price price1 = priceService.findOne(Integer.parseInt(currentSeat[1]));
                    Ticket ticket =  new Ticket(
                            currentSeat[0].split(" ")[0],
                            currentSeat[0].split(" ")[1],
                            user,
                            eventDB,
                            price1);

                    user.getTickets().add(ticket);
                    eventDB.getTickets().add(ticket);
                    tickets.add(ticket);
                }
                if (!checkTicket(tickets, eventId)){
                    return String.format("redirect:/payment?e=%d&seats=%s&error=%b&message=%s", eventId, seats, true, "Unfortunately, one of the seats has already been purchased :(");

                }

                for (Ticket ticket: tickets){
                    ticketService.save(ticket);
                }

                Event event = eventsService.findOne(eventId);
                MailStructure mailStructure = new MailStructure();

                mailStructure.setPrice(price);
                mailStructure.setPlace(event.getHall().getName());
                mailStructure.setEventTimeAndName(LocalDateTime.ofInstant(event.getDate().toInstant(), ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                        + " " + event.getName());
                mailStructure.setTimeOfPurchase(LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("Europe/Moscow")).format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                mailStructure.setSubject("SheduleOfEvents");
                mailStructure.setTickets(tickets);

                bankService.pay(bankCard.getCardNumber(), price);
                if (!user.getEmail().isEmpty()){
                    try {
                        mailService.sendMail( user.getEmail(), mailStructure);
                    }
                    catch (MessagingException e){
                        e.printStackTrace();
                    }
                }
                userDetailsService.save(user);
                eventsService.save(eventDB);

                return "redirect:/payment/successful-payment" ;
            }
        }
        return String.format("redirect:/payment?a=%d&u=%d&e=%d&error=%b", price, id, eventId, true);
    }

    private boolean checkTicket(List<Ticket> tickets, int eventId){
        HashSet<String> ticketsDB = new HashSet<>(ticketService.findAllWhereEventId(eventId).stream().map(t -> t.getRow() + " " + t.getCol()).toList());
        System.out.println(ticketsDB);
        for (Ticket ticket: tickets){
            if (ticketsDB.contains(ticket.getRow() + " " + ticket.getCol())){
                return false;
            }
        }
        return true;
    }


}