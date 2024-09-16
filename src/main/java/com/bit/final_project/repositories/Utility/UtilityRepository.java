package com.bit.final_project.repositories.Utility;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Grn;
import com.bit.final_project.models.Utility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilityRepository extends JpaRepository<Utility,String> {
    Page<Utility> findAllByStatus(Pageable pageable, Status status);

    @Query("SELECT s FROM Utility s WHERE s.status = :status AND " +
            "YEAR(s.date) = :year AND MONTH(s.date) = :month")
    List<Utility> findAllByDate(@Param("status") Status status,
                            @Param("year") int year,
                            @Param("month") int month);
}
