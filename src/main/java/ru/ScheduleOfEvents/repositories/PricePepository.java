package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ScheduleOfEvents.models.Price;

public interface PricePepository extends JpaRepository<Price, Integer> {

}
