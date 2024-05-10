package ru.ScheduleOfEvents.models;


import lombok.Data;

import java.util.List;

@Data
public class MailStructure {
    private int price;
    private String subject;
    private String timeOfPurchase;
    private String place;
    private String eventTimeAndName;
    private List<Ticket> tickets;


}
