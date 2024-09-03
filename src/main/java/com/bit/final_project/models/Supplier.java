package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier {
    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String nic;
    private String supplier_type;
    private String email;
    private String contact;

}
