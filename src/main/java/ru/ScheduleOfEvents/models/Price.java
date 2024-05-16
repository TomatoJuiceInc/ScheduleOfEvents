package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Price")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "price")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "ownerPriceForTT")
    private List<TemporaryTicket> temporaryTickets;

    public Price(int price, Event ownerEvent) {
        this.price = price;
        this.ownerEvent = ownerEvent;
    }

    public Price(int price) {
        this.price = price;
    }
}



