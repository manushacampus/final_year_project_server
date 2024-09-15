package com.bit.final_project.repositories.Payment;

import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Page<Payment> findAllByType(Pageable pageable, PaymentType type);
}
