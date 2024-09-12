package com.bit.final_project.models;

import com.bit.final_project.enums.ColorType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bar")
@Slf4j
public class Bar {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name = "color_type")
    private ColorType colorType;
    private String color;
    private Double length;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bar_angles", referencedColumnName = "id")
    private BarAngles barAngles;
}
