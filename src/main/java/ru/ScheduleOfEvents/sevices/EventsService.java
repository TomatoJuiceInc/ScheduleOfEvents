package ru.ScheduleOfEvents.sevices;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.repositories.EventRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventsService {
    private final EventRepository eventRepository;

    @Autowired
    public EventsService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }


    public List<Event> findEventByDateBefore() {
        return eventRepository.findEventsByDateBefore(new Date());
    }

    public List<Event> findEventByDateAfter() {
        return eventRepository.findEventsByDateAfter(new Date());
    }

    public Event findEventById(Integer id) {
        return eventRepository.findEventById(id);
    }

    @Transactional
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Transactional
    public void updateEvent(Integer id, Event updatedEvent) {
        updatedEvent.setId(id);
        eventRepository.save(updatedEvent);
    }

    @Transactional
    public void delete(Event event) {
        eventRepository.delete(event);
    }
}
