package com.project.backend.services;

import com.project.backend.models.Promotion;
import com.project.backend.models.User;
import com.project.backend.models.Voucher;
import com.project.backend.repositories.VoucherRepository;
import com.project.backend.sec.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

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

    public List<Voucher> findByUserId(Integer id){
        return repository.getByUserID(id).stream().filter(voucher -> voucher.getValidUntil().isAfter(LocalDateTime.now())).collect(Collectors.toList());
    }

    public Voucher findByCode(String code){
        Voucher item = repository.findById(code)
                        .orElseThrow(() -> new NoSuchElementException("Doesn't exist"));
        return item;
    }

    public Voucher add(Voucher voucher){
        try{
            User user = userService.findById(voucher.getBelongToUser());
            Integer mileBefore = user.getTotalMile();
            promotionChecker(voucher.getPromotionId(),user.getId());
            promotionMapper(voucher.getPromotionId(),user);

            voucher.setCode(voucherCodeGenerator());
            voucher.setMileBefore(mileBefore);
            voucher.setIsUsed(false);
            voucher.setValidUntil(LocalDateTime.now().plusDays(30));
            voucher.setIssuedDate(LocalDateTime.now());
            return repository.save(voucher);
        }catch (Exception e){
            throw e;
        }
    }

    public Voucher update(Voucher newItem,String code){
        try{
            Voucher item = findByCode(code);

            item.setValidUntil(newItem.getValidUntil());
            item.setIsUsed(newItem.getIsUsed());
            return repository.save(item);
        }catch (Exception e){
            throw e;
        }
    }

    public Voucher delete(String id) {
        Voucher item = findByCode(id);
        repository.delete(item);
        return item;
    }

    //TODO already use implementation
    public Integer useVoucher(String code, Authentication authentication){
        CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
        Voucher item = findByCode(code);
        Promotion promotion = promotionService.findById(item.getPromotionId());
        if (item.getIsUsed()){
            throw new IllegalArgumentException("This code has been used");
        } else if(item.getBelongToUser() != details.getId()){
            throw new IllegalArgumentException("This code is not your");
        }else if(item.getValidUntil().isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("This code has been expired");
        }
        return promotion.getDiscountAmount();
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

    //TODO TEST THAT MINUS MILE WORK
    private boolean promotionMapper(String promotionId,User user){
        if(promotionId.equals("p_001")){

        }else if(promotionId.equals("p_002")){
            if(user.getTotalMile()-50000<0){
                throw new IllegalArgumentException("Not enough mile");
            }
            user.setTotalMile(user.getTotalMile()-50000);
        }else if(promotionId.equals("p_003")){
            if(user.getTotalMile()-20000<0){
                throw new IllegalArgumentException("Not enough mile");
            }
            user.setTotalMile(user.getTotalMile()-20000);
        }else if(promotionId.equals("p_004")){
            if(user.getTotalMile()-10000<0){
                throw new IllegalArgumentException("Not enough mile");
            }
            user.setTotalMile(user.getTotalMile()-10000);
        }else if(promotionId.equals("p_005")){

        }else if(promotionId.equals("p_006")){

        }else if(promotionId.equals("p_007")){

        }
        return true;
    }

}
