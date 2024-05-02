package ru.ScheduleOfEvents.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Application;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.models.Person;
import ru.ScheduleOfEvents.repositories.EventRepository;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class EventsService {

    private final EventRepository eventRepository;


    @Autowired
    public EventsService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

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
    public void submitApplication(Person client, Event event) {
        // должна быть логика отпраки заявки админу

//        Application application = applicationService.createApplication(client, event);
//        applicationService.save(application);
    }
}