package com.bit.final_project.models;

import com.bit.final_project.enums.ColorType;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "other")
@Slf4j
public class Other {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "color_type")
    private ColorType colorType;
    private String color;
    private Double length;
    private Double  weight;
    private String type;
    @Enumerated(EnumType.STRING)
    private Status status;


}
