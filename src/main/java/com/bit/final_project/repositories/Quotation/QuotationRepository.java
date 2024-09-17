package com.bit.final_project.repositories.Quotation;

import com.bit.final_project.enums.OrderStatus;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Order;
import com.bit.final_project.models.Quotation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,String> {
    Page<Quotation> findAllByStatus(Pageable pageable,Status status);
    Page<Quotation> findAllByStatusAndProgress(Pageable pageable, Status status, OrderStatus orderStatus);
    Page<Quotation> findAll(Pageable pageable);

    List<Quotation> findAllByCustomer(Customer customer, Sort sort);

}
