package com.bit.final_project.repositories.DoorQuotation;

import com.bit.final_project.models.DoorQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorQuotationRepository extends JpaRepository<DoorQuotation,String> {
}
