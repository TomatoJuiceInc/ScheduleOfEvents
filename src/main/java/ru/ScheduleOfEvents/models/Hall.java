package ru.ScheduleOfEvents.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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


    @OneToMany(mappedBy = "hall",  cascade = CascadeType.ALL)
    private List<Event> events;

    public Hall(String name, int count_seats) {
        this.name = name;
        this.count_seats = count_seats;
    }


    public Hall() {

    }
}
