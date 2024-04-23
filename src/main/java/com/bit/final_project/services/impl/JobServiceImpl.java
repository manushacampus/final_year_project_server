package com.bit.final_project.services.impl;

import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.StockItemDto;
import com.bit.final_project.models.Door;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.StockItem;
import com.bit.final_project.repositories.Door.DoorRepository;
import com.bit.final_project.repositories.Job.JobRepository;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
import com.bit.final_project.repositories.Window.WindowRepository;
import com.bit.final_project.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    DoorRepository doorRepository;
    @Autowired
    WindowRepository windowRepository;
    @Autowired
    StockItemRepository stockItemRepository;
    @Autowired
    JobRepository jobRepository;
    @Transactional
    @Override
    public Job createJobForDoor(DoorDto doorDto){
        Job job = new Job();
        Job jobResult = jobRepository.save(job);
        Door door = new Door();
        Door doorResult = doorRepository.save(door);
        StockItem stockItem = new StockItem();
        stockItem.setJob(jobResult);
        stockItem.setDoor(doorResult);
        stockItemRepository.save(stockItem);
        return jobResult;
    }

}
