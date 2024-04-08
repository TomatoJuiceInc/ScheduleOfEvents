package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Price")
@Data
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

    public Price() {}
}
