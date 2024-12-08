package com.admindashboard.e_commerce.e_commerce.model.employee.repository;

import com.admindashboard.e_commerce.e_commerce.model.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
