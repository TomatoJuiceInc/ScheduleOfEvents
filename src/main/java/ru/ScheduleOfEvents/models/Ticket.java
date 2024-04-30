package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
@Getter
@Setter
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
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person ownerTicket;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    public Ticket(String col, String row, Person ownerTicket, Event event, Price price) {
        this.col = col;
        this.row = row;
        this.ownerTicket = ownerTicket;
        this.event = event;
        this.price = price;
    }

    public Ticket(Person ownerTicket, Event event, Price price) {
        this.ownerTicket = ownerTicket;
        this.event = event;
        this.price = price;
    }
}
