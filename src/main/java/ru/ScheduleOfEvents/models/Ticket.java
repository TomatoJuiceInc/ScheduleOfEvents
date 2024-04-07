package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private int col;
    @Column(name = "row")
    private int row;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person ownerTicket;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    public Ticket(Person ownerTicket, Event event, Price price) {
        this.ownerTicket = ownerTicket;
        this.event = event;
        this.price = price;
    }
}
