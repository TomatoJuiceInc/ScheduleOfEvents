package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "Person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "role")
    private String role;


    @Min(value = 1)
    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Event> events;


    @OneToMany(mappedBy = "ownerTicket")
    private List<Ticket> tickets;

    public Person(String username, String email, String phone_number, String role, int age, String password, List<Event> events, List<Ticket> tickets) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.age = age;
        this.password = password;
        this.events = events;
        this.tickets = tickets;
    }
}
