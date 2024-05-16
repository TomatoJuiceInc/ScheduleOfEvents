package ru.ScheduleOfEvents.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Hall")
@Data
@NoArgsConstructor
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

    public Hall(String name, int count_seats, List<Event> events) {
        this.name = name;
        this.count_seats = count_seats;
        this.events = events;
    }

    public Hall(String name) {
        this.name = name;
    }
}
