package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;



@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

}
