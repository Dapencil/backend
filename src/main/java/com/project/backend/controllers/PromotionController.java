package com.project.backend.controllers;

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
    @GetMapping("getById")
    public Promotion findPromotionById(@RequestParam(name = "id") int id){
        return promotionService.findPromotionById(id);
    }
    @PostMapping("add")
    public int addPromotion(@RequestBody Promotion promotion){
        return promotionService.addPromotion(promotion.getId(),promotion.getTitle(),
                promotion.getDescription(),promotion.getDiscountAmount());
    }
}
