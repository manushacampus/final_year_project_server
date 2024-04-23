package com.bit.final_project.controllers.employee;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.services.DoorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/door")
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
}
