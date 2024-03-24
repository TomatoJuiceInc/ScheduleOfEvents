package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person ownerTicket;

    public Person getOwnerTicket() {
        return ownerTicket;
    }
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
    public Ticket(){

    }

    public void setOwnerTicket(Person ownerTicket) {
        this.ownerTicket = ownerTicket;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket(int id) {
        this.id = id;
    }



}
