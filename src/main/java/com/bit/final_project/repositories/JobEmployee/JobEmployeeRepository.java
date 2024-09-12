package com.bit.final_project.repositories.JobEmployee;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Employee;
import com.bit.final_project.models.Job;
import com.bit.final_project.models.JobEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobEmployeeRepository extends JpaRepository<JobEmployee,String> {
    public List<JobEmployee> findByJob(Job job);
    public List<JobEmployee> findAllByStatusAndEmployee(Status status, Employee employee);
}
