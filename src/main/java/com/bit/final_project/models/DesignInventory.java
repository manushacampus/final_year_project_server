package com.bit.final_project.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "design_inventory")
@Slf4j
public class DesignInventory {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "inventory")
    private Inventory inventory;
    @ManyToOne
    @JoinColumn(name = "design")
    private Design design;
}
