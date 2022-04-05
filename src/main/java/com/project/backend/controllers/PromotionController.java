package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Promotion;
import com.project.backend.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public List<Promotion> getPromotions(){
        return promotionService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity findPromotionById(@PathVariable String id){
        try{
            Promotion item = promotionService.findById(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addPromotion(@RequestBody Promotion promotion){
        try{
            Promotion item = promotionService.add(promotion);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updatePromotion(@RequestBody Promotion promotion, @PathVariable String id){
        try{
            Promotion item = promotionService.update(promotion,id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deletePromotion(@PathVariable String id){
        try{
            Promotion item = promotionService.delete(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
