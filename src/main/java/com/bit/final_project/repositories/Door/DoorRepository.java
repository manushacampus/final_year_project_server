package com.bit.final_project.repositories.Door;

import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoorRepository extends JpaRepository<Door,String> {
    public List<Door> findAllByStatus(Status status);
    public Door findByCode(String code);
}
