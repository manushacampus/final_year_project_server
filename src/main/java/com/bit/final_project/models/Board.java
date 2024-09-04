package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "board")
@Slf4j
public class Board {
    @Id
    private String id;
    private String name;
    private Double weight;
    private Double height;
    private Double width;
    private String color;
    private String type;
    private String description;

}
