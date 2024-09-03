package com.bit.final_project.services;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.JobEmployeeDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface JobService {

    Job createJobForDoor(JobDto jobDto, DoorDto doorDto);

    Job updateJobForDoor(JobDto jobDto, DoorDto doorDto);
    Job deleteJobForDoor(String id,String progress);
    List<Job> getJobs();
    Page<JobDto> getJobBYStatusAndProgress(Status status, Progress progress, int page, int size);
    List<Job> getJobEmployeeByStatus(Status status, Employee employee);

//    List<JobEmployee> assignEmployeeJob(String jobId, Employee employee);
    Job getJobById(String id);
    JobEmployeeDto takeJobForEmployee(Job job, Employee employee);

    Job createJobByStock(String stockItemId);

    Job DoneTheJob(String jobId, MultipartFile image) throws IOException;

    Job DoneTheJobByNew(String jobId);
    Job startTheJob(String jobId);

    Job createJobForQuotation();
}
