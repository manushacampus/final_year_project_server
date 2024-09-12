package com.bit.final_project.models;

import com.bit.final_project.enums.HeightOrWidth;
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
    @Enumerated(EnumType.STRING)
    private HeightOrWidth type;
    private Double qty;
    @ManyToOne
    @JoinColumn(name = "inventory")
    private Inventory inventory;
    @ManyToOne
    @JoinColumn(name = "design")
    private Design design;
}
