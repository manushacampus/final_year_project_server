package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.ReportRequestDto;
import com.bit.final_project.services.DashboardService;
import com.bit.final_project.services.ReportService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
@RestController
@RequestMapping("api/employee/dashboard")
@Slf4j
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @GetMapping("")
    @Transactional
    public @ResponseBody
    ResponseEntity<StandardResponse> getDashboard() {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", dashboardService.getDashboard()),HttpStatus.OK
        );
    }
}
