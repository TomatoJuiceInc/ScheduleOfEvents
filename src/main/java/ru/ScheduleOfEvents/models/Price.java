package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Price")
public class Price {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event ownerEvent;
    @OneToMany(mappedBy = "eventPrice")
    private List<Ticket> tickets;



    public Price(int price, Event ownerEvent) {
        this.price = price;
        this.ownerEvent = ownerEvent;
    }
    public Price(){

    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Event getOwnerEvent() {
        return ownerEvent;
    }

    public void setOwnerEvent(Event ownerEvent) {
        this.ownerEvent = ownerEvent;
    }





}
