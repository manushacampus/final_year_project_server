package com.bit.final_project.services;

import com.bit.final_project.dto.DoorDto;
import com.bit.final_project.models.Door;

public interface DoorService {
    Door createDoor(DoorDto dto);
}
