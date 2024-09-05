package com.bit.final_project.services.email;

import javax.mail.MessagingException;
import java.util.Map;

public interface EmailService {

    void sendEmail(String to, String subject, String htmlBody) throws MessagingException;
    void sendVerificationEmail(String to, String subject, Map<String,Object> templateModel) throws MessagingException;
    void sendPurchaseRequest(String to, String subject, Map<String,Object> templateModel) throws MessagingException;
}
