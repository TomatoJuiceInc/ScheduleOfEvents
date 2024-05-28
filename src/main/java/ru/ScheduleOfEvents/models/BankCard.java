package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_card")
@Data
@NoArgsConstructor
public class BankCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "owner")
    private String owner;

    @Column(name = "description")
    private String description;

    @Column(name = "cvc")
    private String cvc;

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvc='" + cvc + '\'' +
                ", duration='" + duration + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Column(name = "duration")
    private String duration;

    @Column(name = "balance")
    private int balance;


}