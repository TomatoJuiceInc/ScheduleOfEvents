package ru.ScheduleOfEvents.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.Price;
import ru.ScheduleOfEvents.repositories.PricePepository;

@Service
@Transactional(readOnly = true)
public class PriceService {
    private final PricePepository pricePepository;

    @Autowired
    public PriceService(PricePepository pricePepository) {
        this.pricePepository = pricePepository;
    }

    public Price findOne(int id){
        return pricePepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Price price){
         pricePepository.save(price);
    }




}
