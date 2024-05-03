package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
}
