package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
