package com.bit.final_project.repositories.Design;

import com.bit.final_project.models.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design,String> {
}
