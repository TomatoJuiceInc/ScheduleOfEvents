package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.TemporaryTicket;
import ru.ScheduleOfEvents.models.User;

import java.util.List;

public interface TemporaryTicketRepository extends JpaRepository<TemporaryTicket, Integer> {
    List<TemporaryTicket> findTemporaryTicketsByOwnerUserForTTAndOwnerEventForTT(User person, Event event);

}
