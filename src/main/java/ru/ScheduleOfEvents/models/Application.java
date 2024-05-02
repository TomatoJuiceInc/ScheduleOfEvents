package ru.ScheduleOfEvents.models;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private User applicantName;  // Имя заявителя

    private Boolean isApproved;    // Статус заявки

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;           // Связанное событие

    // Конструкторы
    public Application() {
    }

    public Application(User applicantName, Event event) {
        this.applicantName = applicantName;
        this.event = event;
        this.isApproved = null;  // Инициализируем как не рассмотренное
    }
}



