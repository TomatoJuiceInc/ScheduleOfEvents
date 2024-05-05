package ru.ScheduleOfEvents.sevices;

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
    public List<Event> findAll(){
        return eventRepository.findAll();
    }
    public List<Event> findAllByName(String name){
        return eventRepository.findAllByNameStartingWith(name);
    }
}
