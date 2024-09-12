package com.bit.final_project.models;

import com.bit.final_project.enums.INVENTORY_TYPE;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "inventory")
@Slf4j
public class Inventory {
    @Id
    private String id;
    private int qty;
    private Double price;
    private String code;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private INVENTORY_TYPE inventoryType;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bar", referencedColumnName = "id")
    private Bar bar;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board", referencedColumnName = "id")
    private Board board;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "other", referencedColumnName = "id")
    private Other other;
}
