package ru.ScheduleOfEvents.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Hall")
public class Hall {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "count_seats")
    private int count_seats;


    @OneToMany(mappedBy = "hall")
    private List<Event> events;

    public Hall(String name, int count_seats) {
        this.name = name;
        this.count_seats = count_seats;
    }

    public Hall() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount_seats() {
        return count_seats;
    }

    public void setCount_seats(int count_seats) {
        this.count_seats = count_seats;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
