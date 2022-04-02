package com.project.backend.controllers;


import com.project.backend.models.Voucher;
import com.project.backend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/voucher")
public class VoucherController {

    @Autowired
    private VoucherService service;

    @GetMapping("generate")
    public String generateVoucherCode(){
        return service.voucherCodeGenerator();
    }
//    @PostMapping("add")
//    public boolean addVoucher(@RequestBody Voucher voucher){
//        service.addVoucher(voucher.getBelongToUser(),voucher.());
//    }
//    @PutMapping("update)
//    public boolean updateVoucher(){
//
//    }
    @DeleteMapping("delete/{id}")
    public boolean deleteVoucher(@PathVariable String id){
        return service.deleteVoucher(id);
    }

}
