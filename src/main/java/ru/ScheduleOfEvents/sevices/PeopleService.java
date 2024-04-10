package ru.ScheduleOfEvents.sevices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

}
