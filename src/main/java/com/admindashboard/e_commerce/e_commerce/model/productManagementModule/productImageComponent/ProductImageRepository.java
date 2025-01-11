package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,String> {
}
