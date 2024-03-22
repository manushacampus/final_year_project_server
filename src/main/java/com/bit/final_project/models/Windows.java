package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "windows")
public class Windows {
    @Id
    private String id;
    private String name;
    private String height;
    private String width;

}
