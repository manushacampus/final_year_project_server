package com.bit.final_project.services.impl;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.Door;
import com.bit.final_project.repositories.Door.DoorRepository;
import com.bit.final_project.services.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoorServiceImpl implements DoorService {
    @Autowired
    DoorRepository doorRepository;
    @Override
    public Door createDoor(DoorDto dto) {
        Door door = Door.init(dto);
        door.setId(dto.getId());
        return doorRepository.save(door);
    }
    @Override
    public List<Door> getDoorList(){
        return doorRepository.findAllByStatus(Status.ACTIVE);
    }

    @Override
    public Door getDoorById(String id) {
        return doorRepository.findById(id).orElseThrow(()-> new EntityExistsException("Door not found with id: " + id));
    }

}
