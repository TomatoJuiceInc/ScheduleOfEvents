package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Person;
import ru.ScheduleOfEvents.models.TemporaryTicket;

import java.util.List;

public interface TemporaryTicketRepository extends JpaRepository<TemporaryTicket, Integer> {
    List<TemporaryTicket> findTemporaryTicketsByOwnerUserForTTAndOwnerEventForTT(Person person, Event event);

}
