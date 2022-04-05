package com.project.backend.services;

import com.project.backend.models.Promotion;
import com.project.backend.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    public List<Promotion> getAll(){
        return repository.findAll();
    }

    public Promotion findById(String id){
        Promotion item = repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Promotion add(Promotion promotion){
        try {
            discountValidation(promotion.getDiscountAmount());
            dateValidation(promotion.getEndDate());
            return repository.save(promotion);
        }catch (Exception e){
            throw e;
        }
    }

    public Promotion update(Promotion newItem,String code) {
        try {
            Promotion item = findById(code);
            discountValidation(newItem.getDiscountAmount());
            dateValidation(newItem.getEndDate());

            item.setDescription(newItem.getDescription());
            item.setDiscountAmount(newItem.getDiscountAmount());
            item.setEndDate(newItem.getEndDate());
            item.setLimitPerUser(newItem.getLimitPerUser());
            item.setTitle(newItem.getTitle());
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Promotion delete(String id) {
        Promotion item = findById(id);
        repository.delete(item);
        return item;
    }

    private boolean discountValidation(Integer number){
        if (number > 0){
            return true;
        }else throw new IllegalArgumentException("discount must be positive");
    }

    private boolean dateValidation(LocalDate end){
        LocalDate toDay = LocalDate.now();
        if(end.isAfter(toDay)){
            return true;
        }else throw  new IllegalArgumentException("end date must be after today");
    }
}
