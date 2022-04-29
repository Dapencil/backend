package com.project.backend.controllers;

import com.project.backend.Util.UtilHelper;
import com.project.backend.models.ResponseModel.EmailMessage;
import com.project.backend.models.ResponseModel.EmailRequest;
import com.project.backend.models.Ticket;
import com.project.backend.services.EmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-voucher")
    public ResponseEntity sendVoucherEmail(@RequestBody EmailMessage email){
        try{
            emailService.sendVoucherEmail(email.getTo(),email.getSubject(), email.getMessage());
            return ResponseEntity.ok("Send success");
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

    @PostMapping("/send-ticket")
    public ResponseEntity sendTicketEmail(@RequestBody EmailRequest request) throws MessagingException, TemplateException, IOException {
//        try{
            Map<String, Object> model = new HashMap<>();
            model.put("from","BKK");
            model.put("too","CNX");
            emailService.sendTicket(request.getTo(),model);
            return ResponseEntity.ok("Send success");
//        }catch (Exception e){
//            return UtilHelper.exceptionMapper(e);
//        }
    }

//    @PostMapping("/send-html")
//    public ResponseEntity sendHtmlEmail(@RequestBody String to, @RequestBody Ticket ticket){
//        try{
//            emailService.sendHtmlEmail(to,ticket);
//            return ResponseEntity.ok("Send success");
//        }catch (Exception e){
//            return UtilHelper.exceptionMapper(e);
//        }
//    }
}
