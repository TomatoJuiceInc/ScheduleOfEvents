package ru.ScheduleOfEvents.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class VipApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User applicantName;

    @Column(name = "is_Approved")
    private Boolean isApproved;

    public VipApplication(User user){
        this.applicantName = user;
        isApproved = false;
    }

}



