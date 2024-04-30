package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.repositories.EventRepository;

import java.util.List;

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
    public List<Event> findAll(){
        return eventRepository.findAll();
    }
}
