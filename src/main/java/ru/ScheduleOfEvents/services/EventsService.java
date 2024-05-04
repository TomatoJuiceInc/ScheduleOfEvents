package ru.ScheduleOfEvents.services;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.User;
import ru.ScheduleOfEvents.repositories.EventRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EventsService {

    private final EventRepository eventRepository;

    public Event findOne(int id){
        return eventRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Event event){
        eventRepository.save(event);
    }


    @Transactional
    public Event createEvent(String name, Date date, String description) {
        Event event = new Event(name, date, description);
        eventRepository.save(event);
        return event;
    }

    @Transactional
    public void submitApplication(User client, Event event) {
        // должна быть логика отпраки заявки админу

//        Application application = applicationService.createApplication(client, event);
//        applicationService.save(application);
    }

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
    public void updateEvent(Integer id, Event updatedEvent) {
        updatedEvent.setId(id);
        eventRepository.save(updatedEvent);
    }

    @Transactional
    public void delete(Event event) {
        eventRepository.delete(event);
    }
}
