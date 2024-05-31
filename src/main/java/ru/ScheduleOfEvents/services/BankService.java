package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.BankCard;
import ru.ScheduleOfEvents.repositories.BankRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public BankCard findByCardNumber(String cardNumber) {
        return bankRepository.findBankCardByCardNumber(cardNumber);
    }


    @Transactional()
    public void pay(String cardNum, int price) {
        BankCard bankCard = findByCardNumber(cardNum);
        bankCard.setBalance(bankCard.getBalance() - price);

    }
    public List<BankCard> findAll(){
        return bankRepository.findAll();
    }

    @Transactional
    public void save(BankCard bankCard){
         bankRepository.save(bankCard);
    }
}
