package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.OtherInventoryDto;
import com.bit.final_project.dto.ReportRequestDto;
import com.bit.final_project.services.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("api/employee/report")
@Slf4j
public class ReportController {
    @Autowired
    ReportService reportService;
    @PostMapping("")
    @Transactional
    public @ResponseBody
    ResponseEntity<StandardResponse> enterOther(@RequestBody ReportRequestDto request) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", reportService.getReport(request.getStartDate(), request.getEndDate())),HttpStatus.OK
        );
    }
}
