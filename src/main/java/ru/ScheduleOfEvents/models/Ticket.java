package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "row")
    private int row;
    @Column(name = "col")
    private int col;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person ownerTicket;
    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price eventPrice;


    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;


    public Ticket() {

    }

    public Person getOwnerTicket() {
        return ownerTicket;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Price getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(Price eventPrice) {
        this.eventPrice = eventPrice;
    }
}
