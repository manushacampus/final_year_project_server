package com.bit.final_project.controllers;

import com.bit.final_project.dto.UserDto;
import com.bit.final_project.models.User;
import com.bit.final_project.services.ForgetPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@Slf4j
@RequestMapping("api/forget-password")
public class ForgetPasswordController {
    @Autowired
    ForgetPasswordService forgetPasswordService;
    @PostMapping("")
    public @ResponseBody
    String emailSender() throws MessagingException {
        String email = "manushakpm1@gmail.com";
        forgetPasswordService.forgetPassword(email);
        return "Success";
    }

}
