package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
     List<Event> findAllByNameStartingWith(String name);

    List<Event> findEventsByDateBefore(Date date);

    List<Event> findEventsByDateAfter(Date date);

    Event findEventById(Integer id);
}
