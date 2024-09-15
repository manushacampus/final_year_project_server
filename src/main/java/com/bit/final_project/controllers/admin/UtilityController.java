package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.JSON;
import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.BarInventoryDto;
import com.bit.final_project.dto.UtilityImageDto;
import com.bit.final_project.dto.entityDto.UtilityDto;
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
@RequestMapping("api/employee/utility")
@Slf4j
public class UtilityController {
    @Autowired
    UtilityService utilityService;

    @PostMapping("")
    public @ResponseBody
    ResponseEntity<StandardResponse> save(@ModelAttribute UtilityImageDto request) throws IOException {
        UtilityDto utilityDto = JSON.stringToObject(request.getUtilityDto(),UtilityDto.class);
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success",
                        UtilityDto.init(utilityService.save(utilityDto, request.getBill()))),HttpStatus.OK
        );
    }
    @GetMapping(value = "/all", params = {"page","size","status"})
    public @ResponseBody
    @Transactional
    ResponseEntity<StandardResponse> getUtilityAll(@Param("@page") int page,
                                                     @Param("@size") int size,
                                                     @Param("@status") String status){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", utilityService.getAll(page,size,status)),HttpStatus.OK
        );
    }
}
