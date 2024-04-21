package ru.ScheduleOfEvents.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailStructure {
    private int price;
    private String subject;
    private String timeOfPurchase;
    private String place;
    private String eventTimeAndName;
    private List<TemporaryTicket> tickets;


}
