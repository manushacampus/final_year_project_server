package com.bit.final_project.repositories.BarAngles;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.BarAngles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarAnglesRepository extends JpaRepository<BarAngles,String> {
    Page<BarAngles> findByCategoryAndSizeAndStatus(Pageable pageable, String cat, String size, Status status);
    Page<BarAngles> findBySizeAndStatus(Pageable pageable,String size, Status status);
    Page<BarAngles> findByCategoryAndStatus(Pageable pageable, String cat, Status status);
    Page<BarAngles> findByStatus(Pageable pageable, Status status);
    Optional<BarAngles> findBySectionNoOrCode(String sectionNo, String code);
}
