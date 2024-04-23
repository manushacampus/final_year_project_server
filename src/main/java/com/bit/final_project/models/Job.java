package com.bit.final_project.models;

import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Job {
    @Id
    private String id;
    private String type;
    private DateTime start;
    private DateTime end;
    private String status;
}
