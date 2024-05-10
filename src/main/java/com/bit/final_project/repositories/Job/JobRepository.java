package com.bit.final_project.repositories.Job;

import com.bit.final_project.enums.Progress;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,String> {
    List<Job> findByStatusAndProgress(Status status, Progress progress);
    List<Job> findByStatus(Status status);
    List<Job> findByProgress(Progress progress);
}
