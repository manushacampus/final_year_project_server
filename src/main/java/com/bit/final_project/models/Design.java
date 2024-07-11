package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "design")
@Slf4j
public class Design {
    @Id
    private String id;
}
