package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Person;

@Repository
public interface EventRepository  extends JpaRepository<Event, Integer> {

}