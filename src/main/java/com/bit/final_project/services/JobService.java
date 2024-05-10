package com.bit.final_project.services;

import com.bit.final_project.dto.StockItemDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.*;

import java.util.List;

public interface JobService {

    Job createJobForDoor(DoorDto doorDto);
    List<Job> getJobs();
    List<Job> getJobBYStatusAndProgress(Status status, Progress progress);

//    List<JobEmployee> assignEmployeeJob(String jobId, Employee employee);
    Job getJobById(String id);
    JobEmployee takeJobForEmployee(Job job,Employee employee);

    Job createJobByStock(String stockItemId);
}
