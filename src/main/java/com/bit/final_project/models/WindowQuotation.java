package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Slf4j
public class WindowQuotation {
    @Id
    private String id;
    private Double height;
    private Double width;
    private String color;
    @ManyToOne
    @JoinColumn(name = "design")
    private Design design;
}
