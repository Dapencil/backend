package com.project.backend.services;

import com.project.backend.models.Promotion;
import com.project.backend.models.Voucher;
import com.project.backend.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository repository;

    public void addVoucher(Integer userId, Promotion promotion){
        Voucher voucher = new Voucher();
        String code = voucherCodeGenerator();

        while(alreadyHaveCode(code)){
            code = voucherCodeGenerator();
        }

        voucher.setCode(code);
        voucher.setBelongToUser(userId);
        voucher.setPromotionId(promotion.getId());
        repository.save(voucher);
    }

    // helper
    private boolean alreadyHaveCode(String code){
        return repository.findById(code).isPresent();
    }

    public String voucherCodeGenerator(){

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;

    }

}
