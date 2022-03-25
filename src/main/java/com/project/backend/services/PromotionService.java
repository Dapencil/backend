package com.project.backend.services;

import com.project.backend.models.Promotion;
import com.project.backend.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository repository;

    public List<Promotion> getPromotion(){
        return repository.findAll();
    }
    public Promotion findPromotionById(int id){
        return repository.findById(id).get();
    }

    public int addPromotion(int id, String title, String description, int discount_amount){
        if(discount_amount <0) { return 0;}
        Promotion promotion = new Promotion();
        promotion.setId(id);
        promotion.setTitle(title);
        promotion.setDescription(description);
        promotion.setDiscountAmount(discount_amount);
//        promotion.setEndDate(end_time); //mai me hai set ka
        return 1;
    }
}
