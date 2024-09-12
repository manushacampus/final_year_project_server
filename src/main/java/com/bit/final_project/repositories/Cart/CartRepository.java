package com.bit.final_project.repositories.Cart;

import com.bit.final_project.models.Cart;
import com.bit.final_project.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,String> {
    List<Cart> findAllByCustomer(Customer customer);
}
