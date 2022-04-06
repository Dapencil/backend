package com.project.backend.controllers;


import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.backend.Util.UtilHelper;
import com.project.backend.models.Voucher;
import com.project.backend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("test")
    public Optional<Voucher> test(@RequestBody ObjectNode node){
        String promotion = node.get("p_id").asText();
        Integer uid = Integer.parseInt(node.get("uid").asText());
        return service.promotionChecker(promotion,uid);
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
