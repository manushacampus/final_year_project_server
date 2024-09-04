package com.bit.final_project.controllers.customer;

import com.bit.final_project.dto.LoginDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.mapper.QuotationMapper;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/customer")
@Slf4j
public class CLoginController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public @ResponseBody
    LoginDto login(@RequestBody UserDto request){
        log.info("controller login user={}",request.getEmail());
        User user=userService.login(request);
        CurrentUser.setUser(user, String.valueOf(user.getUser_role()));
        LoginDto loginDto = LoginDto.init(user);
        return loginDto;
    }
}
