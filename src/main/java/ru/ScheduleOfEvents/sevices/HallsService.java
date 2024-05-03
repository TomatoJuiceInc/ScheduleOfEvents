package ru.ScheduleOfEvents.sevices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Hall;
import ru.ScheduleOfEvents.repositories.HallRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class HallsService {
    private final HallRepository hallRepository;

    @Autowired
    public HallsService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }
}
