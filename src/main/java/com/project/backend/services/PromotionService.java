package com.project.backend.services;

import com.project.backend.models.Airport;
import com.project.backend.models.Promotion;
import com.project.backend.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    public List<Promotion> getPromotion(){
        return repository.findAll();
    }
    public Promotion findPromotionById(String id){
        return repository.findById(id).get();
    }

    public boolean addPromotion(String id, String title, String description, Integer discount_amount, LocalDate endDate){
        if(discount_amount <0) { return false;}
        Promotion promotion = new Promotion();
        promotion.setId(id);
        promotion.setTitle(title);
        promotion.setDescription(description);
        promotion.setDiscountAmount(discount_amount);
        promotion.setEndDate(endDate);
        repository.save(promotion);
        return true;
    }
    public boolean updatePromotion(String id, String title, String description, Integer discountAmount,LocalDate endDate) {
        if (discountAmount<0) { return false; }
        Optional<Promotion> promotion = repository.findById(id);
        if (promotion.isEmpty()) { return false; }
        promotion.get().setTitle(title);
        promotion.get().setDescription(description);
        promotion.get().setDiscountAmount(discountAmount);
        promotion.get().setEndDate(endDate);
        repository.save(promotion.get());
        return true;
    }
    public boolean deletePromotion(String id) {
        Optional<Promotion> promotion = repository.findById(id);
        if (promotion.isPresent()) {
            repository.delete(promotion.get());
            return true;
        }
        return false;
    }
}
