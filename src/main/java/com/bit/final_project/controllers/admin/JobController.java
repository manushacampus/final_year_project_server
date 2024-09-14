package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.JobDoorDto;
import com.bit.final_project.dto.JobWindowDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.WindowDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.security.filters.CurrentUser;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/employee/job")
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
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getJobById(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",JobDto.init(jobService.getJobById(id))),HttpStatus.OK
        );
    }
    @PostMapping("/door")
    public ResponseEntity<StandardResponse> createJobDoor(@ModelAttribute JobDoorDto jobDoorDto){
        JobDto JobDTORequest= JSON.stringToObject(jobDoorDto.getJobDto(),JobDto.class);
        DoorDto doorDTORequest= JSON.stringToObject(jobDoorDto.getDoorDto(),DoorDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.createJobForDoor(JobDTORequest,doorDTORequest)),HttpStatus.OK
        );
    }
    @PostMapping("/window")
    public ResponseEntity<StandardResponse> createJobWindow(@ModelAttribute JobWindowDto jobWindowDto){
        JobDto JobDTORequest= JSON.stringToObject(jobWindowDto.getJobDto(),JobDto.class);
        WindowDto windowDtoDTORequest= JSON.stringToObject(jobWindowDto.getWindowDto(), WindowDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.createJobForWindow(JobDTORequest,windowDtoDTORequest)),HttpStatus.OK
        );
    }
    @PutMapping("/door")
    public ResponseEntity<StandardResponse> updateJobDoor(@ModelAttribute JobDoorDto jobDoorDto){
        JobDto JobDTORequest= JSON.stringToObject(jobDoorDto.getJobDto(),JobDto.class);
        DoorDto doorDTORequest= JSON.stringToObject(jobDoorDto.getDoorDto(),DoorDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.updateJobForDoor(JobDTORequest,doorDTORequest)),HttpStatus.OK
        );
    }
    @DeleteMapping("/door/{id}/{progress}")
    public ResponseEntity<StandardResponse> deleteJobDoor(@PathVariable("id") String id,@PathVariable("progress") String progress){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.deleteJobForDoor(id,progress)),HttpStatus.OK
        );
    }
    @GetMapping()
    public ResponseEntity<StandardResponse> getJobList(){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.getJobs()),HttpStatus.OK
        );
    }
    @GetMapping(params ={"status","progress","page","size"})
    public ResponseEntity<StandardResponse> getJobListByStatusAndProgress( @Param("@status") String status,
                                                                           @Param("@progress") String progress,
                                                                           @Param("@page") int page,
                                                                           @Param("@size") int size){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.getJobBYStatusAndProgress(Status.valueOf(status), Progress.valueOf(progress),page,size)),HttpStatus.OK
        );
    }
    @GetMapping("/employee")
    public ResponseEntity<StandardResponse> getJobListByStatusAndEmployee(@RequestParam("status") String status,@RequestParam("progress") String progress){
        log.info("status={}",Status.valueOf(status));
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),
                        "success",
                        jobService.getJobEmployeeByStatus(Status.valueOf(status),employeeService.getEmployeeById(CurrentUser.getUser().getId()),Progress.valueOf(progress)).stream().map(JobDto::init).collect(Collectors.toList())),
                HttpStatus.OK
        );
    }
    @PostMapping("/assign/{id}")
    ResponseEntity<StandardResponse> assignEmployeeForJob(@PathVariable("id") String id){
        log.info("work!!");
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.takeJobForEmployee(jobService.getJobById(id),employeeService.getEmployeeByUser(CurrentUser.getUser()))),HttpStatus.OK
        );
    }
    @PostMapping("/job-by-product/{id}")
    public ResponseEntity<StandardResponse> createJobByProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.createJobByStock(id)),HttpStatus.OK
        );
    }

    @PutMapping("/done/{id}")
    public ResponseEntity<StandardResponse> doneJob(@PathVariable("id") String id, @ModelAttribute MultipartFile image) throws IOException {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.DoneTheJob(id,image)),HttpStatus.OK
        );
    }
    @PutMapping("/done/new/{id}")
    public ResponseEntity<StandardResponse> doneJobNew(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.DoneTheJobByNew(id)),HttpStatus.OK
        );
    }
    @PutMapping("/start/{id}")
    public ResponseEntity<StandardResponse> startJob(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",jobService.startTheJob(id)),HttpStatus.OK
        );
    }
}
