package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Ticket")
@Data
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
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel ownerTicket;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price eventPrice;

    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

}
