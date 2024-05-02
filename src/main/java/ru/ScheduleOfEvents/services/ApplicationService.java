package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Application;
import ru.ScheduleOfEvents.repositories.ApplicationRepository;
import ru.ScheduleOfEvents.repositories.EventRepository;

@Service
public class ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void approveApplication(int applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found!"));
        application.setIsApproved(true);

        // Удаление связанного события
        eventRepository.deleteById(application.getEvent().getId());
        applicationRepository.save(application);
    }

    @Transactional
    public void rejectApplication(int applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found!"));
        application.setIsApproved(false);

        // Удаление связанного события
        eventRepository.deleteById(application.getEvent().getId());
        applicationRepository.save(application);
    }
}
