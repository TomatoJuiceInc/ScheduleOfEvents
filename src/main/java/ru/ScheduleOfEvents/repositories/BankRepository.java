package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.models.Event;

import java.util.List;

@Repository
public interface BankRepository  extends JpaRepository<BankCard, Integer> {
    BankCard findBankCardByCardNumber(String number);
}
