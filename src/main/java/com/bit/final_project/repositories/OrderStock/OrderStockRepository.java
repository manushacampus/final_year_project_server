package com.bit.final_project.repositories.OrderStock;

import com.bit.final_project.models.Order;
import com.bit.final_project.models.OrderStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStockRepository extends JpaRepository<OrderStock,String> {
    List<OrderStock> findAllByOrder(Order order);

}
