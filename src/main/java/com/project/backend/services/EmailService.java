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
import java.time.LocalDateTime;
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

    public void sendVoucherEmail(String to, String code){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.ngew@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Your voucher");
        simpleMailMessage.setText("Congratulations You get a voucher!"+"\n"+"Voucher:"+code);
        mailSender.send(simpleMailMessage);
    }

    public void sendTicketEmail(String to, Integer bookNo, String name, Integer flightNo, LocalDateTime date, String where){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply.ngew@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Booking Confirmation");
        simpleMailMessage.setText("Dear "+name+",\n"+"Thank you for choosing Ngew Airline! Your reservation has been confirmed.\n"
        +"Your booking number : "+bookNo+"\nName of passenger : "+name+"\nDate : "+date+"\nFlight No. : "+flightNo+"\nFrom "+where);
        mailSender.send(simpleMailMessage);
    }

    public void sendTicketHtml(String to, Map<String, Object> model)  throws MessagingException, TemplateException, IOException{
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
}
