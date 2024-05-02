package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @OneToMany(mappedBy = "event",  cascade = CascadeType.ALL)
    private List<Ticket> ticket;


    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;


    public Event(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public Event(String name, Date date, String description, Person owner, Hall hall) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.owner = owner;
        this.hall = hall;
    }

    public Event() {

    }

}