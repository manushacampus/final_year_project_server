package com.bit.final_project.controllers.employee;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.AddBarAnglesDto;
import com.bit.final_project.dto.UserDto;
import com.bit.final_project.dto.entityDto.BarAnglesDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.services.BarAnglesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/bar-angles")
@Slf4j
public class BarAnglesController {
    @Autowired
    BarAnglesService barAnglesService;
    @PostMapping
    public @ResponseBody
    ResponseEntity<StandardResponse> createBarAngles(@ModelAttribute AddBarAnglesDto barAnglesDto) throws IOException {
        log.info("controller work={}",barAnglesDto.getBarAnglesDto());
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", barAnglesService.addBarAngles(JSON.stringToObject(barAnglesDto.getBarAnglesDto(), BarAnglesDto.class),barAnglesDto.getImage())),HttpStatus.OK
        );
    }
    @GetMapping(params ={"page","size","cat","barSize","status"})
    public @ResponseBody
    ResponseEntity<StandardResponse> getBarAngles(@Param("@page") int page,
                                                  @Param("@size") int size,
                                                  @Param("@cat") String cat,
                                                  @Param("@barSize") String barSize,
                                                  @Param("@status") String status){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", barAnglesService.getAll(page,size,cat,barSize,status)),HttpStatus.OK
        );
    }
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity<StandardResponse> getAllBarAngels() {
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", barAnglesService.getAllBarAngels()),HttpStatus.OK
        );
    }
}
