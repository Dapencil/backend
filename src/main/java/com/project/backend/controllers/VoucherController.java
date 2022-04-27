package com.project.backend.controllers;


import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Voucher;
import com.project.backend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/voucher")
public class VoucherController {

    @Autowired
    private VoucherService service;

    @GetMapping("")
    public List<Voucher> getAll(){
        return service.getAll();
    }

    @GetMapping("/{code}")
    @ResponseBody
    public ResponseEntity getByCode(@PathVariable String code){
        try{
            Voucher item = service.findByCode(code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @GetMapping("/use/{code}")
    @ResponseBody
    public ResponseEntity getDiscount(@PathVariable String code, Authentication authentication){
        try{
            Integer discount = service.useVoucher(code,authentication);
            return ResponseEntity.ok(discount);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("@secService.isMyPage(authentication, #id) or hasRole('MANAGER')")
    @ResponseBody
    public ResponseEntity getByUser(@PathVariable Integer id){
        try{
            List<Voucher> item = service.findByUserId(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity addVoucher(@RequestBody Voucher voucher){
        try{
            //TODO have switch with each promotion
            Voucher item = service.add(voucher);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PutMapping("/{code}")
    @ResponseBody
    public ResponseEntity updateVoucher(@RequestBody Voucher voucher,@PathVariable String code){
        try {
            Voucher item = service.update(voucher,code);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deleteVoucher(@PathVariable String id){
        try{
            Voucher item = service.delete(id);
            return ResponseEntity.ok(item);
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

}
