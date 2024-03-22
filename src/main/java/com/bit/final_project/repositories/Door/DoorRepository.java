package com.bit.final_project.repositories.Door;

import com.bit.final_project.models.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends JpaRepository<Door,String> {
}
