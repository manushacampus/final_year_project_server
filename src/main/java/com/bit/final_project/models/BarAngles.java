package com.bit.final_project.models;

import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bar_angles")
@Slf4j
public class BarAngles {
    @Id
    private String id;
    @Column(name = "section_no")
    private String sectionNo;
    private String name;
    private String code;
    private String thickness;
    private String weight;
    private String category;
    private String size;
    private String image;
    @Enumerated(EnumType.STRING)
    private Status status;

}
