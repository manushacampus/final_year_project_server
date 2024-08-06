package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Slf4j
public class DoorQuotation {
    @Id
    private String id;
    private Long height;
    private Long width;
    private String color;
    @ManyToOne
    @JoinColumn(name = "design")
    private Design design;

}
