package ru.ScheduleOfEvents.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.repositories.TemporaryTicketRepository;
import ru.ScheduleOfEvents.repositories.TicketRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TemporaryTicketService {
    private final PeopleService peopleService;
    private final  EventsService eventsService;
    private final TicketService ticketService;
    private final PriceService priceService;
    private final TemporaryTicketRepository temporaryTicketRepository;



    @Autowired
    public TemporaryTicketService(PeopleService peopleService, EventsService eventsService, TicketService ticketService, PriceService priceService, TemporaryTicketRepository temporaryTicketRepository) {
        this.peopleService = peopleService;
        this.eventsService = eventsService;
        this.ticketService = ticketService;
        this.priceService = priceService;
        this.temporaryTicketRepository = temporaryTicketRepository;
    }

    public List<TemporaryTicket> findAll(){
        return temporaryTicketRepository.findAll();
    }


    @Transactional
    public void save(TemporaryTicket temporaryTicket){
        temporaryTicketRepository.save(temporaryTicket);
    }
    @Transactional
    public void deleteById(int  id){
        temporaryTicketRepository.deleteById(id);
    }

    public List<TemporaryTicket> findAllById(int id, int eventId){
        return temporaryTicketRepository.findTemporaryTicketsByOwnerUserForTTAndOwnerEventForTT(peopleService.findOne(id), eventsService.findOne(eventId));
    }

    @Transactional
    public void saveTickets(int id, int eventId) {
        List<TemporaryTicket> tickets = findAllById(id, eventId);
        Person person = peopleService.findOne(id);
        Event event = eventsService.findOne(eventId);
        for (TemporaryTicket ticket: tickets){
            Price price = priceService.findOne(ticket.getOwnerPriceForTT().getId());
            Ticket currentTicket = new Ticket( ticket.getCol(),  ticket.getRow(),  person,  event,  priceService.findOne(ticket.getOwnerPriceForTT().getId()));
            price.getTickets().add(currentTicket);
            ticketService.save(currentTicket);
            priceService.save(price);
            person.getTickets().add(currentTicket);
            event.getTickets().add(currentTicket);
            deleteById(ticket.getId());
        }
        peopleService.save(person);
        eventsService.save(event);
    }
}
