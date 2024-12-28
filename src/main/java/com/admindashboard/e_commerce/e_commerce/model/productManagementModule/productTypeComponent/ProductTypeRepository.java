package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,String> {
    Optional<ProductType>findByTypeName(String typeName);
}
