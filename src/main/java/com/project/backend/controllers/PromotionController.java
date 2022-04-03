package com.project.backend.controllers;

import com.project.backend.models.Airport;
import com.project.backend.models.Promotion;
import com.project.backend.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("getAll")
    public List<Promotion> getPromotions(){
        return promotionService.getPromotion();
    }
    @GetMapping("get/{id}")
    public Promotion findPromotionById(@PathVariable String id){
        return promotionService.findPromotionById(id);
    }
    @PostMapping("add")
    public boolean addPromotion(@RequestBody Promotion promotion){
        return promotionService.addPromotion(promotion.getId(),promotion.getTitle(),
                promotion.getDescription(),promotion.getDiscountAmount(),promotion.getEndDate());
    }
    @PutMapping("update/{id}")
    public boolean updatePromotion(@RequestBody Promotion promotion, @PathVariable String id){
        return promotionService.updatePromotion(promotion.getId(),promotion.getTitle(),
                promotion.getDescription(),promotion.getDiscountAmount(),promotion.getEndDate());
    }
    @DeleteMapping("delete/{id}")
    public boolean deletePromotion(@PathVariable String id){
        return promotionService.deletePromotion(id);
    }
}
