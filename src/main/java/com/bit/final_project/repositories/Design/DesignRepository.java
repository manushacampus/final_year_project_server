package com.bit.final_project.repositories.Design;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Design;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design,String> {

    Page<Design> findAllByStatus(Pageable pageable, Status status);
    Page<Design> findAll(Pageable pageable);
}
