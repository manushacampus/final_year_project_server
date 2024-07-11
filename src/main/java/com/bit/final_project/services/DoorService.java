package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.models.Door;

import java.util.List;

public interface DoorService {
    Door createDoor(DoorDto dto);
    List<Door> getDoorList();

    Door getDoorById(String id);
}
