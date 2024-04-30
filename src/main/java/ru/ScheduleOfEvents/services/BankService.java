package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.repositories.BankRepository;

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


    @Transactional()
    public void pay(String cardNum,  int price){
        BankCard bankCard = findByCardNumber(cardNum);
        bankCard.setBalance(bankCard.getBalance() - price);

    }
}
