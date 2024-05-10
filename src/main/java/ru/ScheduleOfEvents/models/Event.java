package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Event")
@Data
@NoArgsConstructor
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

    @Column(name = "duration")
    private long duration;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "age")
    private String age;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private User owner;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "ownerEventForTT")
    private List<TemporaryTicket> temporaryTickets;

    @OneToMany(mappedBy = "ownerEvent")
    private List<Price> prices;

    @ManyToOne
    @JoinColumn(name = "hall_id", referencedColumnName = "id")
    private Hall hall;



    public Event(String name, Date date, long duration, String description) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

    public Event(String name, Date date, String description) {
    }


    public int compareAge(String str1){
        str1 = str1.substring(1);
        String str2 = this.age.substring(1);
        return Integer.parseInt(str1) - Integer.parseInt(str2);
    }



}
