package com.bit.final_project.services.email;

import com.bit.final_project.component.ViewResolverConfig;
import com.bit.final_project.configs.EmailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Service
@Slf4j
public class JavaEmailService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
@Autowired
private EmailConfig emailConfig;
    @Autowired
    ViewResolverConfig viewResolverConfig;

    @Override
    public void sendEmail(String to, String subject, String htmlBody) throws MessagingException {
        log.info("email to={}",to);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true,"utf-8");
        helper.setText(htmlBody,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(emailConfig.getUserName());
        mailSender.send(mimeMessage);

    }

    @Override
    public void sendVerificationEmail(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody= viewResolverConfig.templateEngine().process("verificationEmail.html",thymeleafContext);
        sendEmail(to,subject,htmlBody);
    }

    @Override
    public void sendPurchaseRequest(String to, String subject, Map<String, Object> templateModel) throws MessagingException {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        String htmlBody= viewResolverConfig.templateEngine().process("inventoryRequest.html",thymeleafContext);
        sendEmail(to,subject,htmlBody);
    }

}
