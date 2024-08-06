package com.bit.final_project.repositories.Quotation;

import com.bit.final_project.models.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,String> {
}
