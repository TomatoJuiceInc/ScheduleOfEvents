package ru.ScheduleOfEvents.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Ticket;
import ru.ScheduleOfEvents.repositories.TicketRepository;

@Service
@Transactional(readOnly = true)
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket findOne(int id){
        return ticketRepository.findById(id).orElse(null);
    }
    @Transactional
    public void  save(Ticket ticket){
         ticketRepository.save(ticket);
    }

}
