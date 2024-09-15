package com.bit.final_project.repositories.Salary;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,String> {
    Page<Salary> findAllByStatus(Pageable pageable, Status status);
    Page<Salary> findAllByStatusAndEmployee(Pageable pageable, Status status, Employee employee);
    @Query("SELECT s FROM Salary s WHERE s.status = :status AND " +
            "YEAR(s.date) = :year AND MONTH(s.date) = :month")
    Page<Salary> findAllByStatusAndDate(Pageable pageable,
                                        @Param("status") Status status,
                                        @Param("year") int year,
                                        @Param("month") int month);
    @Query("SELECT s FROM Salary s WHERE s.status = :status AND s.employee = :employee AND " +
            "YEAR(s.date) = :year AND MONTH(s.date) = :month")
    Page<Salary> findAllByStatusAndEmployeeAndDate(Pageable pageable,
                                                           @Param("status") Status status,
                                                           @Param("employee") Employee employee,
                                                           @Param("year") int year,
                                                           @Param("month") int month);

}
