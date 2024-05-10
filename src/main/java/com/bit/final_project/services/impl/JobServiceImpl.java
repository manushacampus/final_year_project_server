package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.dto.StockItemDto;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Door.DoorRepository;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.Job.JobRepository;
import com.bit.final_project.repositories.JobEmployee.JobEmployeeRepository;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
import com.bit.final_project.repositories.Window.WindowRepository;
import com.bit.final_project.services.JobService;
import com.bit.final_project.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobEmployeeRepository jobEmployeeRepository;
    @Autowired
    StockService stockService;
    @Transactional
    @Override
    public Job createJobForDoor(DoorDto doorDto){
        if (doorRepository.findByCode(doorDto.getCode())!=null){
            throw new EntityExistsException("exists code");
        }
        Door doorResult = doorRepository.save(Door.init(doorDto));
        StockItem stockItem = new StockItem();
        stockItem.setId(Generator.getUUID());
        stockItem.setDoor(doorResult);
        stockItem.setStatus(Status.ACTIVE);
        stockItemRepository.save(stockItem);
        Job job = new Job();
        job.setId(Generator.getUUID());
        job.setType("Door");
        job.setProgress(Progress.NEW);
        job.setStatus(Status.ACTIVE);
        job.setStockItem(stockItem);
        Job jobResult = jobRepository.save(job);

        return jobResult;
    }

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getJobBYStatusAndProgress(Status status, Progress progress) {
        if (progress.equals(Progress.ALL)){
            return jobRepository.findByStatus(status);
        }
        return jobRepository.findByStatusAndProgress(status,progress);
    }
//    @Transactional
//    @Override
//    public List<JobEmployee> assignEmployeeJob(String jobId,Employee employee){
//        Job job = jobRepository.findById(jobId).get();
//        employeeList.forEach(user->{
//            Employee employee = employeeRepository.findById(user).get();
//            JobEmployee jobEmployee= new JobEmployee();
//            jobEmployee.setJob(job);
//            jobEmployee.setEmployee(employee);
//            jobEmployeeRepository.save(jobEmployee);
//        });
//        return jobEmployeeRepository.findByJob(job);
//    }

    @Override
    public Job getJobById(String id) {
        return jobRepository.findById(id).orElseThrow(() -> new EntityExistsException("Job not found with id: " + id));
    }

    @Override
    @Transactional
    public JobEmployee takeJobForEmployee(Job job, Employee employee) {
        job.setProgress(Progress.PENDING);
        jobRepository.save(job);
        JobEmployee jobEmployee = new JobEmployee();
        jobEmployee.setId(Generator.getUUID());
        jobEmployee.setEmployee(employee);
        jobEmployee.setJob(job);
        return jobEmployeeRepository.save(jobEmployee);
    }

    @Transactional
    @Override
    public Job createJobByStock(String stockItemId) {
        StockItem stockItem = stockService.getStockItemById(stockItemId);
        Job job = new Job();
        job.setId(Generator.getUUID());
        job.setType(stockItem.getType());

        return jobRepository.save(job);
    }


}
