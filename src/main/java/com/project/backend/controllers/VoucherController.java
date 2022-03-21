package com.project.backend.controllers;


import com.project.backend.services.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/voucher")
public class VoucherController {

    @Autowired
    private VoucherService service;

    @GetMapping("generate")
    public String generateVoucherCode(){
        return service.voucherCodeGenerator();
    }

}
