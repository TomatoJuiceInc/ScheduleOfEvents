package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.BankCard;

@Repository
public interface BankRepository  extends JpaRepository<BankCard, Integer> {
    BankCard findBankCardByCardNumber(String number);
}
