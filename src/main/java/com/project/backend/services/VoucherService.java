package com.project.backend.services;

import com.project.backend.models.Voucher;
import com.project.backend.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository repository;

    public List<Voucher> getAll(){
        return repository.findAll();
    }

    public Voucher findByCode(String code){
        Voucher item = repository.findById(code)
                        .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Voucher add(Voucher voucher){
        try{
            //TODO maybe fix
            voucher.setCode(voucherCodeGenerator());
            return repository.save(voucher);
        }catch (Exception e){
            throw e;
        }
    }

    public Voucher update(Voucher newItem,String code){
        try{
            //TODO maybe fix
            Voucher item = findByCode(code);

            item.setValidUntil(newItem.getValidUntil());
            item.setIsUsed(newItem.getIsUsed());
            return repository.save(newItem);
        }catch (Exception e){
            throw e;
        }
    }

    public Voucher delete(String id) {
        Voucher item = findByCode(id);
        repository.delete(item);
        return item;
    }

    public Optional<Voucher> promotionChecker(String promotionId,Integer userId){
        return repository.checkPromotion(promotionId,userId);
    }

    private String voucherCodeGenerator(){

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        String generatedString = "";
        Random random = new Random();

        do {
            generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
        while(alreadyHaveCode(generatedString));

        return generatedString;

    }

    // helper
    private boolean alreadyHaveCode(String code){
        return repository.findById(code).isPresent();
    }

    // ถ้ามันทำได้ไม่ throw คือโอเค
    private void promotionMapper(String promotionId){

    }


}
