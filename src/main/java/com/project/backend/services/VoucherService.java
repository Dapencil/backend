package com.project.backend.services;

import com.project.backend.models.User;
import com.project.backend.models.Voucher;
import com.project.backend.repositories.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class VoucherService {

    @Autowired
    private VoucherRepository repository;

    @Autowired
    private UserService userService;

    @Autowired PromotionService promotionService;

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
            User user = userService.findById(voucher.getBelongToUser());
            Integer mileBefore = user.getTotalMile();
            promotionChecker(voucher.getPromotionId(),user.getId());
            promotionMapper(voucher.getPromotionId(),user);

            voucher.setCode(voucherCodeGenerator());
            voucher.setMileBefore(mileBefore);
            voucher.setIsUsed(false);
            voucher.setValidUntil(LocalDateTime.now().plusDays(30));
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

    private boolean promotionChecker(String promotionId,Integer userId){
        if(repository.checkPromotion(promotionId,userId).isPresent()){
            throw new IllegalArgumentException("You've already exchanged, calm down until next month. ");
        }
        return true;
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

    private boolean alreadyHaveCode(String code){
        return repository.findById(code).isPresent();
    }

    // power of hard code
    private boolean promotionMapper(String promotionId,User user){
        if(promotionId.equals("p_001")){

        }else if(promotionId.equals("p_002")){
            if(user.getTotalMile()-50000<0){
                throw new IllegalArgumentException("No enough mile");
            }
            user.setTotalMile(user.getTotalMile()-50000);
        }else if(promotionId.equals("p_003")){
            if(user.getTotalMile()-20000<0){
                throw new IllegalArgumentException("No enough mile");
            }
            user.setTotalMile(user.getTotalMile()-20000);
        }else if(promotionId.equals("p_004")){
            if(user.getTotalMile()-10000<0){
                throw new IllegalArgumentException("No enough mile");
            }
            user.setTotalMile(user.getTotalMile()-10000);
        }else if(promotionId.equals("p_005")){

        }else if(promotionId.equals("p_006")){

        }else if(promotionId.equals("p_007")){

        }
        return true;
    }


}
