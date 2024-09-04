package com.bit.final_project.mapper;

import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.models.Job;

public class JobMapper {
    public static JobDto convertToDto(Job job){
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());

        return jobDto;
    }
}
