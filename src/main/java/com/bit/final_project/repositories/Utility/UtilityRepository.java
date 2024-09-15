package com.bit.final_project.repositories.Utility;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Utility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityRepository extends JpaRepository<Utility,String> {
    Page<Utility> findAllByStatus(Pageable pageable, Status status);
}
