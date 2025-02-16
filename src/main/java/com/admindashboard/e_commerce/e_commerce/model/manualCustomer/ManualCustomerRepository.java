package com.admindashboard.e_commerce.e_commerce.model.manualCustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManualCustomerRepository extends JpaRepository<ManualCustomer,String> {
}
