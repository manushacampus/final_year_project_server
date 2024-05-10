package com.bit.final_project.repositories.Employee;

import com.bit.final_project.models.Employee;
import com.bit.final_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    public Employee findByUser(User user);
}
