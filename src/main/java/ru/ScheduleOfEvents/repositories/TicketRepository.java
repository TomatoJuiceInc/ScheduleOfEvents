package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Ticket;

@Repository
public interface  TicketRepository extends JpaRepository<Ticket, Integer> {
}
