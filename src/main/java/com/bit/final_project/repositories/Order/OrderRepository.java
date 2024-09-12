package com.bit.final_project.repositories.Order;

import com.bit.final_project.enums.PRODUCT_TYPE;
import com.bit.final_project.enums.Status;
import com.bit.final_project.models.Customer;
import com.bit.final_project.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    Page<Order> findAllByCustomer(Pageable pageable, Customer customer);
}
