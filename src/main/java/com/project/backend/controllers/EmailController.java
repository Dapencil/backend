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
            emailService.sendVoucherEmail(email.getTo(),email.getCode());
            return ResponseEntity.ok("Send success");
        }catch (Exception e){
            return UtilHelper.exceptionMapper(e);
        }
    }

//    @PostMapping("/send-ticket")
//    public ResponseEntity sendTicketEmail(@RequestBody EmailRequest request){
//        try{
//            emailService.sendTicketEmail(request.getTo(),request.getBookNo(),request.getName(),request.getFlightNo(),
//                    request.getDate(),request.getWhere());
//            return ResponseEntity.ok("Send success");
//        }catch (Exception e){
//            return UtilHelper.exceptionMapper(e);
//        }
//    }

//    @PostMapping("/send-Html")
//    public ResponseEntity sendTicketEmail(@RequestBody EmailRequest request) throws MessagingException, TemplateException, IOException {
////        try{
//            Map<String, Object> model = new HashMap<>();
//            model.put("bookNo",request.getBookNo());
//            model.put("name",request.getName());
//            model.put("flightNo",request.getFlightNo());
//            model.put("date",request.getDate());
//            model.put("where",request.getWhere());
//            emailService.sendTicketHtml(request.getTo(),model);
//            return ResponseEntity.ok("Send success");
////        }catch (Exception e){
////            return UtilHelper.exceptionMapper(e);
////        }
//    }
}
