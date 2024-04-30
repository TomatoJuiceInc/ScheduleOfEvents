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

    @Column(name = "name")
    private String name;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "surname")
    private String surname;

    @Min(value = 1)
    @Column(name = "age")
    private int age;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "owner",  cascade = CascadeType.ALL)
    private List<Event> events;


    @OneToMany(mappedBy = "ownerTicket",  cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Person(String username, String familyName, String name, String surname, String email, String phone_number, String password, List<Event> events) {
        this.username = username;
        this.familyName = familyName;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
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
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
