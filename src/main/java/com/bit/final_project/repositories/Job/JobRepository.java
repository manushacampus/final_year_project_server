package com.bit.final_project.repositories.Job;

import com.bit.final_project.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
}
