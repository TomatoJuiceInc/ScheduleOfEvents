package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "Person")
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


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Person(String username, String email, String phone_number, String role, int age, String password, List<Event> events) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.age = age;
        this.password = password;
        this.events = events;
    }
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public  Person(){

    }

    public Person(String username, String email, String phone_number, String role, int age, String password) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
        this.age = age;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
