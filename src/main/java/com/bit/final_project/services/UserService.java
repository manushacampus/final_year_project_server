package com.bit.final_project.services;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.exceptions.UserNotFoundException;
import com.bit.final_project.exceptions.http.UnauthorizeException;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User login(UserDto request){
        log.info("User Login loginId = {}",request.getEmail());
        User user = userRepository.findByEmail(request.getEmail());
        if(user == null || !passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new UnauthorizeException("INVALID_CREDENTIALS","Login credentials are invalid");
        }
//        if(!user.getStatus().equals(User.Status.ACTIVE)){
//            throw  new UnauthorizeException("You Account Is Deactivated.Please Contact HR");
//        }

        return user;

    }
    public  User register(UserDto request){
        log.info("User Login loginId = {}",request.getEmail());
        User user = new User();
        user.setId(Generator.getUUID());
        user.setFirst_name(request.getFirstName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }
    public User getUserById(String userId) {
        log.info("Get User id={}",userId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new UserNotFoundException("Bad Request","user not found for id = "+userId);
        }
        return user.get();
    }

}
