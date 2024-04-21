package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "User_Model")
@Data
@NoArgsConstructor
public class UserModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "roles")
    private String roles;

    @Min(value = 1)
    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Event> events;

    @OneToMany(mappedBy = "ownerTicket")
    private List<Ticket> tickets;
}
