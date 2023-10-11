package com.bit.final_project.controllers;

import com.bit.final_project.dto.UserDto;
import com.bit.final_project.models.User;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.security.filters.UserHelper;
import com.bit.final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public @ResponseBody
    UserDto register(@RequestBody UserDto request){
       User user = userService.register(request);
       UserDto userDto = new UserDto();
       userDto.setFirstName(user.getFirst_name());
       return userDto;
    }

    @PostMapping("/login")
    public @ResponseBody
    UserDto login(@RequestBody UserDto request){
        User user=userService.login(request);
        CurrentUser.setUser(user, String.valueOf(user.getUser_role()));
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirst_name());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
