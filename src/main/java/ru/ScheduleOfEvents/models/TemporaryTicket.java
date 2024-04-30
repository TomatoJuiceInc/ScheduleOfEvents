package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "temporary_ticket")
@Data
@NoArgsConstructor
public class TemporaryTicket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "row")
    private String row;


    @Override
    public String toString() {
        return "TemporaryTicket{" +
                "id=" + id +
                ", row='" + row + '\'' +
                ", col='" + col + '\'' +
                ", time=" + time +
                '}';
    }

    @Column(name = "col")
    private String col;


    @Column(name = "time")

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event ownerEventForTT;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person ownerUserForTT;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price ownerPriceForTT;

    public TemporaryTicket(String col, String row, Date time, Event ownerEventForTT, Person ownerUserForTT, Price ownerPriceForTT) {
        this.row = row;
        this.col = col;
        this.time = time;
        this.ownerEventForTT = ownerEventForTT;
        this.ownerUserForTT = ownerUserForTT;
        this.ownerPriceForTT = ownerPriceForTT;
    }
}
