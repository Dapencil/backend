package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.ResponseModel.EmailMessage;
import com.project.backend.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage email){
        try{
            emailService.sendVoucherEmail(email.getTo(),email.getSubject(), email.getMessage());
            return ResponseEntity.ok("Send success");
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }
}
