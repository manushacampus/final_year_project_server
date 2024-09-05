package com.bit.final_project.controllers.admin;

import com.bit.final_project.commons.StandardResponse;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.services.DoorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employee/door")
@Slf4j
public class DoorController {

    @Autowired
    DoorService doorService;
    @PostMapping
    public @ResponseBody
    String registration(@RequestBody DoorDto doorDto){
        doorService.createDoor(doorDto);
        return "Success";
    }
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getDoorById(@PathVariable("id") String id){
        return new ResponseEntity<>(
                new StandardResponse(HttpStatus.OK.value(),"success", DoorDto.init(doorService.getDoorById(id))),HttpStatus.OK
        );
    }

}
