package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Application;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.repositories.ApplicationRepository;
import ru.ScheduleOfEvents.repositories.EventRepository;

import java.util.List;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void approveApplication(int applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found!"));
        Event event = application.getEvent();
        event.setStatus(true);
        System.out.println(application.getEvent().getId());
        eventRepository.save(event);

        applicationRepository.deleteById(applicationId);


    }

    @Transactional
    public void rejectApplication(int applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found!"));
        Event event = application.getEvent();
        System.out.println(event.getName());
        event.setStatus(false);

        // Удаление связанного события
        //TODO изменение статуса ивента
//        eventRepository.deleteById(application.getEvent().getId());
        applicationRepository.deleteById(applicationId);

        eventRepository.save(event);
    }

    public List<Application> findAll() {
        return applicationRepository.findAll();
    }
}
