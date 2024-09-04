package com.bit.final_project.models;

import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "purchase")
@Slf4j
public class Purchase {
    @Id
    private String id;
    private String qty;
    @Enumerated(EnumType.STRING)
    private Status status;

}
