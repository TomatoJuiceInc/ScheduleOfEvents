package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.File;
import java.util.List;


@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "avatarFileName")
    private String avatarFileName;

    @Column(name = "name")
    private String name;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "surname")
    private String surname;

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

    @Min(value = 0)
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner",  cascade = CascadeType.ALL)
    private List<Event> events;

    @OneToMany(mappedBy = "ownerTicket",  cascade = CascadeType.ALL)
    private List<Ticket> tickets;



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
