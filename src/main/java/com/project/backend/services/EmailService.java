package com.project.backend.services;

import com.project.backend.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {
    @Autowired
    private final JavaMailSender mailSender;
    @Autowired
    private Configuration configuration;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendVoucherEmail(String to, String subject,String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.ngew@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }
    public void sendTicket(String to, Map<String, Object> model)  throws MessagingException, TemplateException, IOException{
        MimeMessage message = mailSender.createMimeMessage();
        //setMedia
        MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        //add attachment
        //helper.addAttachment("logo.png",new ClassPathResource("logo.png"));
        Template t = configuration.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setTo(to);
        helper.setText(html, true);
        helper.setSubject("Booking Confirmation");
        helper.setFrom("noreply.ngew@gmail.com","Reservations");
        mailSender.send(message);
    }
//    public void sendHtmlEmail(String to, Ticket ticket) throws MessagingException, UnsupportedEncodingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        String mailContent ="<p><b>Dear Khun"+ticket.getUserId()+"</b></p>";
//        mailContent+="<p>Thanks for choosing us! Your reservation has been confirmed.</p>";
//        mailContent+="<p><b>Your Booking Details</b></p>";
//        mailContent+="<p>Booking id: "+ticket.getTicketId()+"</p>";
//        helper.setFrom("noreply.ngew@gmail.com","Reservations");
//        helper.setTo(to);
//        helper.setSubject("Ngew Air Booking Confirmation");
//        helper.setText(mailContent,true);
//        mailSender.send(message);
//    }
}
