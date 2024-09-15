package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.SalaryRequestDto;
import com.bit.final_project.dto.entityDto.SalaryDto;
import com.bit.final_project.dto.entityDto.UtilityDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.Salary;
import com.bit.final_project.models.Utility;
import com.bit.final_project.repositories.Salary.SalaryRepository;
import com.bit.final_project.services.EmployeeService;
import com.bit.final_project.services.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    SalaryRepository salaryRepository;
    @Autowired
    FilesStorageService filesStorageService;
    @Autowired
    EmployeeService employeeService;
    @Override
    public Salary getSalaryById(String id) {
        return salaryRepository.findById(id).orElseThrow(()-> new EntityExistsException("Salary not found with id: " + id));
    }

    @Override
    public Salary save(SalaryRequestDto request, MultipartFile bill, String employeeId) throws IOException {
        Salary salary = new Salary();
        salary.setId(Generator.getUUID());
        salary.setSalary(request.getSalary());
        salary.setAdditional(request.getAdditional());
        salary.setEmployee(employeeService.getEmployeeById(employeeId));
        salary.setStatus(Status.ACTIVE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (request.getDate() != null && !request.getDate().isEmpty()) {
            salary.setDate(LocalDate.parse(request.getDate(), formatter));
        }


        String extension= FilenameUtils.getExtension(bill.getOriginalFilename());
        AppFile appImage = new AppFile(
                "salary",
                Generator.getUUID(),
                extension,
                bill.getInputStream()
        );
        AppFile saveImage=filesStorageService.save(appImage);
        salary.setInvoice(saveImage.getImageName());

        return salaryRepository.save(salary);
    }

    @Override
    public Page<SalaryDto> getAll(int page, int size, String status,String employeeId,String date) {
        Pageable pageableRequest = PageRequest.of(page,size);
        if (employeeId != null && !employeeId.isEmpty() && date != null && !date.isEmpty()){
            log.info("work------employee and Date");
            LocalDate salaryDate = LocalDate.parse(date);
            int year = salaryDate.getYear();
            int month = salaryDate.getMonthValue();
            Employee employee = employeeService.getEmployeeById(employeeId);
            return salaryRepository.findAllByStatusAndEmployeeAndDate(pageableRequest,Status.valueOf(status),employee,year,month).map(SalaryDto::init);
        }
        else if (date != null && !date.isEmpty()) {
            log.info("work------only Date");
            LocalDate salaryDate = LocalDate.parse(date);
            int year = salaryDate.getYear();
            int month = salaryDate.getMonthValue();
            return salaryRepository.findAllByStatusAndDate(pageableRequest,Status.valueOf(status),year,month).map(SalaryDto::init);
        }
        else if (employeeId != null && !employeeId.isEmpty()) {
            log.info("work------only Employee");
            Employee employee = employeeService.getEmployeeById(employeeId);
            return salaryRepository.findAllByStatusAndEmployee(pageableRequest,Status.valueOf(status),employee).map(SalaryDto::init);
        }
        else {
            log.info("work------All");
            return salaryRepository.findAllByStatus(pageableRequest,Status.valueOf(status)).map(SalaryDto::init);
        }


    }

//    @Override
//    public Salary update(String id, SalaryDto request) {
//        return null;
//    }
}
