package ru.ScheduleOfEvents.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.sevices.BankService;
import ru.ScheduleOfEvents.sevices.PeopleService;
import ru.ScheduleOfEvents.sevices.TemporaryTicketService;

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
    public String bookSeats(BankCard bankCard,
                            @PathVariable("price") int price,
                            @PathVariable("id") int id,  @PathVariable("event") int eventId) {
        BankCard bCard = bankService.findByCardNumber(bankCard.getCardNumber());
        System.out.println(bankCard);
        if (bCard == null){
            return String.format("redirect:/payment?a=%d&u=%d&e=%d&error=%b", price, id, eventId, true);
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
        }
        return String.format("redirect:/payment?a=%d&u=%d&e=%d&error=%b", price, id, eventId, true);
    }


}