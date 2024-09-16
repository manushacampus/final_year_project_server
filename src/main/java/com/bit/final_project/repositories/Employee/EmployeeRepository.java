package com.bit.final_project.repositories.Employee;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    public Employee findByUser(User user);
    List<Employee> findAllByStatus(Status status);
}
