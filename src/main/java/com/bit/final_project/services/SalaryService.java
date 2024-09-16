package com.bit.final_project.services;

import com.bit.final_project.dto.SalaryRequestDto;
import com.bit.final_project.dto.entityDto.SalaryDto;
import com.bit.final_project.models.Salary;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SalaryService {

    Salary getSalaryById(String id);
    Salary save(SalaryRequestDto request, MultipartFile bill, String employeeId) throws IOException;

    Page<SalaryDto> getAll(int page, int size, String status,String employeeId,String date);
//    Salary update(String id,SalaryDto request);

    List<Salary> getSalaryByDate(int year,int month);
}
