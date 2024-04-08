package ru.ScheduleOfEvents.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.models.Event;
import ru.ScheduleOfEvents.repositories.BankRepository;
import ru.ScheduleOfEvents.repositories.EventRepository;

@Service
@Transactional(readOnly = true)
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankCard findByCardNumber(String cardNumber){

        return bankRepository.findBankCardByCardNumber(cardNumber);
    }


    @Transactional
    public void pay(String id,  int price){
        BankCard bankCard = findByCardNumber(id);
        bankCard.setBalance(bankCard.getBalance() - price);

    }
}
