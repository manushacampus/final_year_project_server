package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.JobDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/job")
@Slf4j
public class JobController {
    @GetMapping
    public ResponseEntity<StandardResponse> getJob(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",""),HttpStatus.OK
        );
    }
}
