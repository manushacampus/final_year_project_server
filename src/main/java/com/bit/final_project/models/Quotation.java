package com.bit.final_project.models;

import com.bit.final_project.enums.DESIGN_TYPE;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Slf4j
public class Quotation {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private DESIGN_TYPE type;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "window_quot", referencedColumnName = "id")
    private WindowQuotation windowQuotation;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "door_quot", referencedColumnName = "id")
    private DoorQuotation doorQuotation;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
}
