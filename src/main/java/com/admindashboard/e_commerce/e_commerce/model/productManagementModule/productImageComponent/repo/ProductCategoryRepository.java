package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.repo;

import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
      ProductCategory findByCode(String code);
}
