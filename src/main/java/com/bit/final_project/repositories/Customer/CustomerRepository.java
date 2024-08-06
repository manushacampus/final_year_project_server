package com.bit.final_project.repositories.Customer;

import com.bit.final_project.models.Customer;
import com.bit.final_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    Customer findByUser(User user);
}
