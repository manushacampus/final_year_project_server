package com.bit.final_project.services.impl;

import com.bit.final_project.commons.Generator;
import com.bit.final_project.commons.storage.model.AppFile;
import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.dto.entityDto.DoorDto;
import com.bit.final_project.dto.entityDto.JobDto;
import com.bit.final_project.dto.entityDto.JobEmployeeDto;
import com.bit.final_project.dto.entityDto.WindowDto;
import com.bit.final_project.enums.*;
import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.EntityExistsException;
import com.bit.final_project.models.*;
import com.bit.final_project.repositories.Door.DoorRepository;
import com.bit.final_project.repositories.Employee.EmployeeRepository;
import com.bit.final_project.repositories.Job.JobRepository;
import com.bit.final_project.repositories.JobEmployee.JobEmployeeRepository;
import com.bit.final_project.repositories.StockItem.StockItemRepository;
import com.bit.final_project.repositories.Window.WindowRepository;
import com.bit.final_project.security.filters.CurrentUser;
import com.bit.final_project.services.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
    @Autowired
    DoorService doorService;
    @Autowired
    WindowService windowService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    FilesStorageService filesStorageService;
    @Transactional
    @Override
    public Job createJobForDoor(JobDto jobDto, DoorDto doorDto){
        log.info("job={}",jobDto.getDueDate());
        log.info("door={}",doorDto.getCode());
        if (doorRepository.findByCode(doorDto.getCode())!=null){
            throw new EntityExistsException("exists code");
        }
        Door doorResult = doorRepository.save(Door.init(doorDto));
        StockItem stockItem = new StockItem();
        stockItem.setId(Generator.getUUID());
        stockItem.setDoor(doorResult);
        stockItem.setStatus(Status.INACTIVE);
        stockItem.setType(PRODUCT_TYPE.DOOR);
        stockItem.setQty(jobDto.getQty());
        stockItem.setPrice(jobDto.getUnitPrice());
        stockItemRepository.save(stockItem);
        Job job = new Job();
        job.setId(Generator.getUUID());
        job.setType(PRODUCT_TYPE.DOOR);
        job.setCreation_type(CREATION_TYPE.NEW);
        job.setProgress(Progress.NEW);
        job.setStatus(Status.ACTIVE);
        job.setQty(jobDto.getQty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (jobDto.getDueDate() != null && !jobDto.getDueDate().isEmpty()) {
            job.setDueDate(LocalDate.parse(jobDto.getDueDate(), formatter));
        }
        job.setDescription(jobDto.getDescription());
        job.setStockItem(stockItem);
        Job jobResult = jobRepository.save(job);

        return jobResult;
    }

    @Override
    @Transactional
    public Job createJobForWindow(JobDto jobDto, WindowDto windowDto) {
        log.info("job={}",jobDto.getDueDate());
        log.info("window={}",windowDto.getCode());
        if (windowRepository.findByCode(windowDto.getCode())!=null){
            throw new EntityExistsException("exists code");
        }
        Windows windowResult = windowRepository.save(Windows.init(windowDto));
        StockItem stockItem = new StockItem();
        stockItem.setId(Generator.getUUID());
        stockItem.setWindows(windowResult);
        stockItem.setStatus(Status.INACTIVE);
        stockItem.setType(PRODUCT_TYPE.WINDOWS);
        stockItem.setQty(jobDto.getQty());
        stockItem.setPrice(jobDto.getUnitPrice());
        stockItemRepository.save(stockItem);
        Job job = new Job();
        job.setId(Generator.getUUID());
        job.setType(PRODUCT_TYPE.WINDOWS);
        job.setCreation_type(CREATION_TYPE.NEW);
        job.setProgress(Progress.NEW);
        job.setStatus(Status.ACTIVE);
        job.setQty(jobDto.getQty());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (jobDto.getDueDate() != null && !jobDto.getDueDate().isEmpty()) {
            job.setDueDate(LocalDate.parse(jobDto.getDueDate(), formatter));
        }
        job.setDescription(jobDto.getDescription());
        job.setStockItem(stockItem);
        Job jobResult = jobRepository.save(job);

        return jobResult;
    }

    @Override
    @Transactional
    public Job updateJobForDoor(JobDto jobDto, DoorDto doorDto) {
        log.info("updateJobForDoor job id={}",jobDto.getId());
        log.info("updateJobForDoor door id={}",doorDto.getId());
        Door doorTest =doorRepository.findByCode(doorDto.getCode());
//        if (doorTest!=null){
//
//            throw new EntityExistsException("exists code");
//        }
        Door door = doorService.getDoorById(doorDto.getId());
        Job job = findById(jobDto.getId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (jobDto.getDueDate() != null && !jobDto.getDueDate().isEmpty()) {
            job.setDueDate(LocalDate.parse(jobDto.getDueDate(), formatter));
        }
        job.setQty(jobDto.getQty());

        door.setCode(doorDto.getCode());
        door.setName(doorDto.getName());
        door.setHeight(doorDto.getHeight());
        door.setWidth(doorDto.getWidth());
        log.info("befor if door type={}",doorDto.getType());
        if (doorDto.getType() != null && !doorDto.getType().isEmpty()) {
            log.info("door type={}",doorDto.getType());
            door.setType(DoorType.valueOf(doorDto.getType()));
        }
        door.setDoorColor(doorDto.getDoorColor());
        if (doorDto.getFillingType() != null && !doorDto.getFillingType().isEmpty()) {
            door.setFillingType(DoorFillingType.valueOf(doorDto.getFillingType()));
        }
        door.setBoardColor(doorDto.getBoardColor());
        door.setGlassColor(doorDto.getGlassColor());
        if (doorDto.getTypeOfBoard() != null && !doorDto.getTypeOfBoard().isEmpty()) {
            door.setTypeOfBoard(TypeOfBoard.valueOf(doorDto.getTypeOfBoard()));
        }
        door.setBoardThickness(doorDto.getBoardThickness());
        door.setGlassThickness(doorDto.getGlassThickness());
        doorRepository.save(door);
        return jobRepository.save(job);
    }

    @Override
    public Job deleteJobForDoor(String id,String progress) {
        Job job = findById(id);
        if (Progress.NEW.equals(Progress.valueOf(progress)) ||
                Progress.PENDING.equals(Progress.valueOf(progress)) ){
            List<JobEmployee> k= jobEmployeeRepository.findByJob(job);
            k.forEach(ks->{
                ks.setStatus(Status.INACTIVE);
            });
            jobEmployeeRepository.saveAll(k);
            job.setStatus(Status.INACTIVE);
            return jobRepository.save(job);
        }
        throw new BadRequestException("Cannot Delete Processing or Completed Job");
    }

    public Job findById(String id){
        return jobRepository.findById(id).orElseThrow(()-> new EntityExistsException("Job not found with id: " + id));
    }

    public List<Job> getJobs(){
        return jobRepository.findAll();
    }

    @Override
    public Page<JobDto> getJobBYStatusAndProgress(Status status, Progress progress, int page, int size) {
        log.info("page={}",page);
        log.info("size={}",size);
        Pageable pageableRequest = PageRequest.of(page,size);
        if (progress.equals(Progress.ALL)){
            return jobRepository.findByStatus(pageableRequest,status).map(JobDto::init);
        }
        return jobRepository.findByStatusAndProgress(pageableRequest,status,progress).map(JobDto::init);
    }

    @Override
    public List<Job> getJobEmployeeByStatus(Status status, Employee employee,Progress progress) {
        log.info("service status={}",status);
        log.info("service employee={}",employee.getUser_id());
        List<JobEmployee> e= jobEmployeeRepository.findAllByStatusAndEmployee(status,employee);
        List<Job> jobList = new ArrayList<>();
        for (JobEmployee jobEmployee : e) {
            log.info("list={}",jobEmployee.getJob().getQty());
            if (Progress.ALL.equals(progress)){
                jobList.add(jobEmployee.getJob());
            }
            if (jobEmployee.getJob().getProgress().equals(progress)) {
                jobList.add(jobEmployee.getJob());
            }
        }
        return jobList;
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
    public JobEmployeeDto takeJobForEmployee(Job job, Employee employee) {
        job.setProgress(Progress.PENDING);
        jobRepository.save(job);
        JobEmployee jobEmployee = new JobEmployee();
        jobEmployee.setId(Generator.getUUID());
        jobEmployee.setEmployee(employee);
        jobEmployee.setJob(job);
        jobEmployee.setStatus(Status.ACTIVE);
        return JobEmployeeDto.init(jobEmployeeRepository.save(jobEmployee));
    }

    @Transactional
    @Override
    public Job createJobByStock(String stockItemId) {
        StockItem stockItem = stockService.getStockItemById(stockItemId);
        Job job = new Job();
        job.setId(Generator.getUUID());
        job.setType(stockItem.getType());
        job.setCreation_type(CREATION_TYPE.PRODUCT);
        job.setQty(1);
        job.setDueDate(LocalDate.now());
        job.setProgress(Progress.NEW);
        job.setStatus(Status.ACTIVE);
        job.setStockItem(stockItem);
        return jobRepository.save(job);
    }

    @Override
    @Transactional
    public Job DoneTheJob(String jobId, MultipartFile image) throws IOException {
        Job job = getJobById(jobId);
        job.setProgress(Progress.DONE);
        if (job.getCreation_type().equals(CREATION_TYPE.NEW)){
            if (image==null || image.isEmpty()){
                throw new BadRequestException("Product Image is empty!");
            }
            log.info("type={}",job.getCreation_type());
            log.info("type 2={}",job.getType());
                log.info("Doortype={}",job.getType());
                String extension= FilenameUtils.getExtension(image.getOriginalFilename());
                AppFile appImage = new AppFile(
                        "product",
                        Generator.getUUID(),
                        extension,
                        image.getInputStream()
                );
                AppFile saveProductImage=filesStorageService.save(appImage);
            if (job.getType().equals(PRODUCT_TYPE.DOOR)) {
                Door door = doorService.getDoorById(job.getStockItem().getDoor().getId());
                door.setImage(saveProductImage.getImageName());
                door.setStatus(Status.ACTIVE);
                doorRepository.save(door);
            }
            if (job.getType().equals(PRODUCT_TYPE.WINDOWS)) {
                Windows windows = windowService.getWindowById(job.getStockItem().getWindows().getId());
                windows.setImage(saveProductImage.getImageName());
                windows.setStatus(Status.ACTIVE);
                windowRepository.save(windows);
            }
                StockItem stockItem = stockService.getStockItemById(job.getStockItem().getId());
                stockItem.setStatus(Status.ACTIVE);
                stockItemRepository.save(stockItem);

        }
        if (job.getCreation_type().equals(CREATION_TYPE.PRODUCT)){
            if (job.getType().equals(PRODUCT_TYPE.DOOR)){
                StockItem stockItem = stockService.getStockItemById(job.getStockItem().getId());
                stockItem.setQty(stockItem.getQty()+1);
                stockItemRepository.save(stockItem);
            }
        }

        return jobRepository.save(job);
    }
    @Override
    @Transactional
    public Job DoneTheJobByNew(String jobId){
        Job job = getJobById(jobId);
        job.setProgress(Progress.DONE);
        if (job.getCreation_type().equals(CREATION_TYPE.NEW)){
            log.info("type={}",job.getCreation_type());
            log.info("type 2={}",job.getType());
            if (job.getType().equals(PRODUCT_TYPE.DOOR)){
                log.info("Doortype={}",job.getType());
                Door door = doorService.getDoorById(job.getStockItem().getDoor().getId());
                door.setStatus(Status.ACTIVE);
                doorRepository.save(door);
                StockItem stockItem = stockService.getStockItemById(job.getStockItem().getId());
                stockItem.setStatus(Status.ACTIVE);
                stockItemRepository.save(stockItem);
            }
        }
        if (job.getCreation_type().equals(CREATION_TYPE.PRODUCT)){
            if (job.getType().equals(PRODUCT_TYPE.DOOR)){
                StockItem stockItem = stockService.getStockItemById(job.getStockItem().getId());
                stockItem.setQty(stockItem.getQty()+1);
                stockItemRepository.save(stockItem);
            }
        }

        return jobRepository.save(job);
    }

    @Override
    public Job startTheJob(String jobId) {
        Job job = getJobById(jobId);
        job.setProgress(Progress.PROCESSING);
        return jobRepository.save(job);
    }

    @Override
    public Job createJobForQuotation() {

        return null;
    }


}
