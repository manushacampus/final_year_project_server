package com.bit.final_project.dto;

import com.bit.final_project.enums.Status;
import org.joda.time.DateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class JobDto {
    private String id;
    private String type;
    private DateTime start;
    private DateTime end;
    private String progress;
    private DateTime dueDate;
    private String status;
}
