package com.bit.final_project.services;

import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.registrationDto.EmployeeRegisterDto;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.User.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface EmployeeService {
    Employee register(UserDto userDto, EmployeeDto employeeDto, MultipartFile profilePicture)throws IOException;
    Page<EmployeeDto> getAll(int page, int size);
    int getAllActiveEmployeeCount();

    Employee getEmployeeById(String id);
    Employee getEmployeeByUser(User user);
    Employee updateProfilePic(MultipartFile file)throws IOException;

    Employee updateProfile(EmployeeDto employeeDto);
}
