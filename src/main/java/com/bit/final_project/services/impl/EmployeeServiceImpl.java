package com.bit.final_project.services.impl;

import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.mapper.EmployeeMapper;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.User.UserRepository;
import com.bit.final_project.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Transactional
    public Employee register(UserDto userDto, EmployeeDto employeeDto){
        log.info("register employee email={}",userDto.getEmail());
        if (userDto.getEmail().isEmpty() || userDto.getEmail()==""){
            throw new BadRequestException("email cant be null");
        }
        Employee employee = new Employee();
        employee.setDesignation(employeeDto.getDesignation());
        employee.setStatus(Status.ACTIVE);
        User user = User.init(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User userResponse = userRepository.save(user);
        employee.setUser(userResponse);
        return employeeRepository.save(employee);
    }

    public Page<EmployeeDto> getAll(int page,int size){
        Pageable pageableRequest = PageRequest.of(page,size);
        Page<EmployeeDto> employeesPage= employeeRepository.findAll(pageableRequest).map(EmployeeMapper::convertToDTO);
        return employeesPage;
    }
}
