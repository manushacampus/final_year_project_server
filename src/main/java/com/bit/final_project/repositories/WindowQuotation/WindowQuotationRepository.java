package com.bit.final_project.repositories.WindowQuotation;


import com.bit.final_project.models.WindowQuotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindowQuotationRepository extends JpaRepository<WindowQuotation,String> {
}
