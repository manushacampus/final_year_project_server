package com.bit.final_project.services;

import com.bit.final_project.dto.registrationDto.EmployeeRegisterDto;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Employee register(EmployeeRegisterDto request){
        log.info("register employee email={}",request.getUserDto().getEmail());
        Employee employee = Employee.init(request.getEmployeeDto());
        User user = User.init(request.getUserDto());
        user.setPassword(passwordEncoder.encode(request.getUserDto().getPassword()));
        User userResponse = userRepository.save(user);
        employee.setUser(userResponse);
        return employeeRepository.save(employee);
    }
}
