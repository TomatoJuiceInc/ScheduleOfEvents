package ru.ScheduleOfEvents.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Hall")
@Data
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
}
