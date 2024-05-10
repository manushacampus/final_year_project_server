package com.bit.final_project.models;

import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Job {
    @Id
    private String id;
    private String type;
    private DateTime start;
    private DateTime end;
    @Enumerated(EnumType.STRING)
    private Progress progress;
    private DateTime due_date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private StockItem stockItem;
}
