package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    //    @ManyToMany
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Collection<Role> roles;
    private String role;

    //    @Min(value = 1)
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    private List<Event> events;

    @OneToMany(mappedBy = "ownerTicket")
    private List<Ticket> tickets;
}
