package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.StockItemDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.services.EmployeeService;
import com.bit.final_project.services.JobService;
import com.bit.final_project.services.StockService;
import com.bit.final_project.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/job")
@Slf4j
public class JobController {
    @Autowired
    JobService jobService;
    @Autowired
    StockService stockService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;
    @GetMapping("/id")
    public ResponseEntity<StandardResponse> getJob(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",""),HttpStatus.OK
        );
    }
    @PostMapping("/door")
    public ResponseEntity<StandardResponse> createJobDoor(@RequestBody DoorDto doorDto){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.createJobForDoor(doorDto)),HttpStatus.OK
        );
    }
    @GetMapping()
    public ResponseEntity<StandardResponse> getJobList(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.getJobs()),HttpStatus.OK
        );
    }
    @GetMapping(params ={"status","progress"})
    public ResponseEntity<StandardResponse> getJobListByStatusAndProgress(@Param("@page") String status, @Param("@size") String progress){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.getJobBYStatusAndProgress(Status.valueOf(status), Progress.valueOf(progress))),HttpStatus.OK
        );
    }
    @PostMapping("/assign/{id}/{uid}")
    ResponseEntity<StandardResponse> assignEmployeeForJob(@PathVariable("id") String id,@PathVariable("uid") String uid){
        log.info("work!!");
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.takeJobForEmployee(jobService.getJobById(id),employeeService.getEmployeeByUser(userService.getUserById(uid)))),HttpStatus.OK
        );
    }
    @PostMapping("/job-by-product/{id}")
    public ResponseEntity<StandardResponse> createJobByProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.createJobByStock(id)),HttpStatus.OK
        );
    }
}
