package com.bit.final_project.models;

import com.bit.final_project.enums.CREATION_TYPE;
import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Job {
    @Id
    private String id;
    private LocalDateTime start;
    private LocalDateTime end;
    private int qty;
    @Column(name = "due_date")
    private LocalDate dueDate;
    private String description;
    @Enumerated(EnumType.STRING)
    private PRODUCT_TYPE type;
    @Enumerated(EnumType.STRING)
    private CREATION_TYPE creation_type;
    @Enumerated(EnumType.STRING)
    private Progress progress;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    @ManyToOne
    @JoinColumn(name = "quotation", referencedColumnName = "id")
    private Quotation quotation;
    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private StockItem stockItem;

}
