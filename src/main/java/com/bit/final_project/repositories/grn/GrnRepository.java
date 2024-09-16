package com.bit.final_project.repositories.grn;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Grn;
import com.bit.final_project.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrnRepository extends JpaRepository<Grn,String> {
    List<Grn> findAllByPurchase(Purchase purchase);

    @Query("SELECT s FROM Grn s WHERE s.status = :status AND " +
            "YEAR(s.date) = :year AND MONTH(s.date) = :month")
    List<Grn> findAllByDate(@Param("status") Status status,
                                        @Param("year") int year,
                                        @Param("month") int month);

}
