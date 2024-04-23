package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.models.Job;

public interface JobService {

    Job createJobForDoor(DoorDto doorDto);
}
