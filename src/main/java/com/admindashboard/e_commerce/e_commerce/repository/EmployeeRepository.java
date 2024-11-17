package com.admindashboard.e_commerce.e_commerce.repository;

import com.admindashboard.e_commerce.e_commerce.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
