package com.bit.final_project.repositories.OrderStock;

import com.bit.final_project.models.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock,String> {
}
