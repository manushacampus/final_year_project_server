package com.bit.final_project.repositories.Bar;

import com.bit.final_project.models.Bar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends JpaRepository<Bar,String> {
}
