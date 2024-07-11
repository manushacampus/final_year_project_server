package com.bit.final_project.services;


import com.bit.final_project.dto.UserDto;
import com.bit.final_project.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public User login(UserDto request);
    public  User register(UserDto request);
    public User getUserById(String userId);
    public String test(MultipartFile multiImage) throws IOException;
    User findUserByEmail(String email);


}
