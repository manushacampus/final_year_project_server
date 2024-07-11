package com.bit.final_project.services;

import com.bit.final_project.models.User;

import javax.mail.MessagingException;

public interface ForgetPasswordService {
    public User forgetPassword(String email) throws MessagingException;
}
