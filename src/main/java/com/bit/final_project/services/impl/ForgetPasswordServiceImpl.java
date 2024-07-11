package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.User.UserRepository;
import com.bit.final_project.services.ForgetPasswordService;
import com.bit.final_project.services.UserService;
import com.bit.final_project.services.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Override
    public User forgetPassword(String email) throws MessagingException {
        User user = userService.findUserByEmail(email);
        String resetCode= Generator.getRandomNumberAsString();
        user.setVerifyCode(resetCode);
        Map<String,Object> templateModel = new HashMap<>();
        templateModel.put("link",resetCode);
        templateModel.put("name",email);
        emailService.sendVerificationEmail(email,"Verification Mail",templateModel);
        return userRepository.save(user);
    }
}
