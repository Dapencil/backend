package com.project.backend.Util;

import com.project.backend.models.Promotion;
import com.project.backend.models.Voucher;
import com.project.backend.services.PromotionService;
import com.project.backend.services.UserService;
import com.project.backend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UtilHelper {

    @Autowired
    private UserService userService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private VoucherService voucherService;

    public static ResponseEntity exceptionMapper(Exception e){
        if(e instanceof IllegalArgumentException)
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        else if(e instanceof NoSuchElementException)
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void birthDayPromotion(String promotionId,Integer userId){
        Optional<Voucher> checker = voucherService.promotionChecker(promotionId,userId);
        Promotion promotion = promotionService.findById(promotionId);
//        User user = userService.findById(userId);
        if(checker.isPresent()){
            throw new IllegalArgumentException("Already use");
        }else{

        }




    }

    private void milePromotion(String promotionId,Integer userId){
        Optional<Voucher> item = voucherService.promotionChecker(promotionId,userId);

        if(item.isPresent()){

        }
        Promotion promotion = promotionService.findById(promotionId);

//        User user = userService.findById(userId);
    }

    private void covidPromotion(String promotionId,Integer userId){
        Promotion promotion = promotionService.findById(promotionId);
//        User user = userService.findById(userId);
    }

}
