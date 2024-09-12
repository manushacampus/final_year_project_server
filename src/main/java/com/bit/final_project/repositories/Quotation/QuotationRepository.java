package com.bit.final_project.repositories.Quotation;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,String> {
    List<Quotation> findAllByStatus(Status status);
    List<Quotation> findAll();

    List<Quotation> findAllByCustomer(Customer customer);

}
