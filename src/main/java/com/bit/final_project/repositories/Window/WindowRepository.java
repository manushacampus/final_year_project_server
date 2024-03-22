package com.bit.final_project.repositories.Window;

import com.bit.final_project.models.Windows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindowRepository extends JpaRepository<Windows,String> {
}
