package com.bit.final_project.dto.entityDto;

import com.bit.final_project.models.Job;
import lombok.Data;

@Data
public class JobDto {
    private String id;
    private String type;
    private String start;
    private String end;
    private String creationType;
    private String progress;
    private String dueDate;
    private String description;
    private int qty;
    private String status;
    private StockItemDto stockItem;

    public static JobDto init(Job job){
        JobDto jobDto = new JobDto();
        jobDto.setId(job.getId());
        jobDto.setType(String.valueOf(job.getType()));
        jobDto.setProgress(String.valueOf(job.getProgress()));
        jobDto.setCreationType(String.valueOf(job.getCreation_type()));
        jobDto.setStockItem(StockItemDto.init(job.getStockItem()));
        jobDto.setDescription(job.getDescription());
        jobDto.setDueDate(String.valueOf(job.getDueDate()));
        jobDto.setQty(job.getQty());
        jobDto.setStatus(String.valueOf(job.getStatus()));
//        jobDto.setCreateBy(EmployeeDto.init(job.getCreateBy()));

        return jobDto;
    }
}
