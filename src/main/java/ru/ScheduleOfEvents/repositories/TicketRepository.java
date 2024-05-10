package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Ticket;

import java.util.List;

@Repository
public interface  TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByEventId(int id);
}
