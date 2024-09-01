package com.bit.final_project.repositories.Other;

import com.bit.final_project.models.Other;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherRepository extends JpaRepository<Other,String> {
}
