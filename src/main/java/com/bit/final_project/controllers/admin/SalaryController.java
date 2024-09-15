package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.SalaryImageDto;
import com.bit.final_project.dto.SalaryRequestDto;
import com.bit.final_project.dto.UtilityImageDto;
import com.bit.final_project.dto.entityDto.SalaryDto;
import com.bit.final_project.dto.entityDto.UtilityDto;
import com.bit.final_project.services.SalaryService;
import com.bit.final_project.services.UtilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.IOException;
@RestController
@RequestMapping("api/employee/salary")
@Slf4j
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @PostMapping("")
    public @ResponseBody
    ResponseEntity<StandardResponse> save(@ModelAttribute SalaryImageDto request) throws IOException {
        SalaryRequestDto salaryDto = JSON.stringToObject(request.getSalaryDto(), SalaryRequestDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",
                        SalaryDto.init(salaryService.save(salaryDto, request.getBill(),request.getEmployeeId()))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/all")
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getUtilityAll(   @RequestParam("page") int page,
                                                      @RequestParam("size") int size,
                                                      @RequestParam("status") String status,
                                                      @RequestParam(value = "employeeId", required = false) String employeeId,
                                                      @RequestParam(value = "date", required = false) String date){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", salaryService.getAll(page,size,status,employeeId,date)),HttpStatus.OK
        );
    }
}
