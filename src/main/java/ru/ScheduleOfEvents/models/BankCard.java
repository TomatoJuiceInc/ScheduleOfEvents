package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bank_card")
@Data
@NoArgsConstructor
public class BankCard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "owner")
    private String owner;

    @Column(name = "description")
    private String description;

    @Column(name = "cvc")
    private String cvc;


    @Column(name = "duration")
    private String duration;

    @Column(name = "balance")
    private int balance;








}