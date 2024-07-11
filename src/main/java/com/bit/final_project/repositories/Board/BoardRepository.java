package com.bit.final_project.repositories.Board;

import com.bit.final_project.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,String> {
}
