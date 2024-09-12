package com.bit.final_project.repositories.Feedback;

import com.bit.final_project.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,String> {
}
