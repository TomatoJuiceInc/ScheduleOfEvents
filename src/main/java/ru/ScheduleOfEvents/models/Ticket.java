package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "col")
    private String col;
    @Column(name = "row")
    private String row;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User ownerTicket;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    public Ticket(String col, String row, User ownerTicket, Event event, Price price) {
        this.col = col;
        this.row = row;
        this.ownerTicket = ownerTicket;
        this.event = event;
        this.price = price;
    }

    public Ticket(User ownerTicket, Event event, Price price) {
        this.ownerTicket = ownerTicket;
        this.event = event;
        this.price = price;
    }

    public Ticket(String col, String row, Event event, Price price) {
        this.col = col;
        this.row = row;
        this.event = event;
        this.price = price;
    }
}
