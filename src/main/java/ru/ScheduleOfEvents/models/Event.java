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
    @Column(name = "age")
    private String age;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "ownerEventForTT")
    private List<TemporaryTicket> temporaryTickets;

    @OneToMany(mappedBy = "ownerEvent")
    private List<Price> prices;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;

    public Event(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }

    public Event(String name, Date date, String description, Person owner, Hall hall, String age) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.owner = owner;
        this.hall = hall;
        this.age = age;
    }

    public Event() {

    }
    public int compareAge(String str1){
        str1 = str1.substring(1);
        String str2 = this.age.substring(1);
        return Integer.parseInt(str1) - Integer.parseInt(str2);
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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