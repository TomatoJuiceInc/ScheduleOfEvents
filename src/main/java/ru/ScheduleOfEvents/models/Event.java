package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Hall getHall() {
        return hall;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTickets(List<Ticket> tickets) {
        this.ticket = tickets;
    }


}