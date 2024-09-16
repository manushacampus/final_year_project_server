package com.bit.final_project.repositories.Payment;

import com.bit.final_project.enums.PaymentType;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,String> {
    Page<Payment> findAllByType(Pageable pageable, PaymentType type);

    @Query(value = "SELECT * FROM payment " +
            "WHERE EXTRACT(YEAR FROM created_at) = :year " +
            "AND EXTRACT(MONTH FROM created_at) = :month " +
            "AND type = :type", nativeQuery = true)
    List<Payment> findByYearAndMonthAndType(@Param("year") int year,
                                            @Param("month") int month,
                                            @Param("type") String type);

}
