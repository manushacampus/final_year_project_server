package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.EmployeeDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.enums.Designation;
import com.bit.final_project.enums.Status;
import com.bit.final_project.enums.UserRole;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.mapper.EmployeeMapper;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.User.UserRepository;
import com.bit.final_project.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    FilesStorageService filesStorageService;

    @Transactional
    @Override
    public Employee register(UserDto userDto, EmployeeDto employeeDto,MultipartFile profilePicture) throws IOException {
        log.info("register employee email={}",userDto.getEmail());
        log.info("register employee email={}",userDto.getLastName());
        if (employeeDto.getDesignation() == null && employeeDto.getDesignation().isEmpty()) {
            throw new NullPointerException("Designation cant be null");
        }
        User existingUser  = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            throw new EntityExistsException("User with email " + userDto.getEmail() + " already exists");
        }
        if (profilePicture==null || profilePicture.isEmpty()){
            throw new BadRequestException("Profile picture is empty!");
        }
        if (userDto.getEmail().isEmpty() || userDto.getEmail()==""){
            throw new BadRequestException("email cant be null");
        }
        String extension= FilenameUtils.getExtension(profilePicture.getOriginalFilename());
        AppFile image = new AppFile(
                "employee",
                Generator.getUUID(),
                extension,
                profilePicture.getInputStream()
        );
        AppFile saveEmployeeImage=filesStorageService.save(image);
        Employee employee = new Employee();
        employee.setDesignation(Designation.valueOf(employeeDto.getDesignation()));
        employee.setStatus(Status.ACTIVE);
        User user = User.init(userDto);
        user.setUser_role(UserRole.EMPLOYEE);
        user.setImage(saveEmployeeImage.getImageName());
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

    @Override
    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElseThrow(()-> new EntityExistsException("Employee not found with id: " + id));
    }

    @Override
    public Employee getEmployeeByUser(User user) {
        return employeeRepository.findByUser(user);
    }
}
