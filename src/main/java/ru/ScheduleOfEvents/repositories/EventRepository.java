package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.Event;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, PagingAndSortingRepository<Event, Integer> {
    List<Event> findAllByNameStartingWith(String name);
    List<Event> findAllByCategoryIsStartingWith(String category);
    List<Event> findAllByNameStartingWithOrCategoryStartingWith(String name,String category);
    List<Event> findEventsByDateBefore(Date date);

    List<Event> findEventsByDateAfter(Date date);

    List<Event> findByDateAfterOrderByDateAsc(Date date);

    List<Event> findByDateBeforeOrderByDateAsc(Date date); /*findByDateBeforeOrderByDateDesc*/

    Event findEventById(Integer id);
}
