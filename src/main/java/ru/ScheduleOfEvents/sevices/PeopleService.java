package ru.ScheduleOfEvents.sevices;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Person;
import ru.ScheduleOfEvents.repositories.PeopleRepository;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        return peopleRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = findOne(id);

        personToBeUpdated.setUsername(updatedPerson.getUsername());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
        personToBeUpdated.setPassword(updatedPerson.getPassword());
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
