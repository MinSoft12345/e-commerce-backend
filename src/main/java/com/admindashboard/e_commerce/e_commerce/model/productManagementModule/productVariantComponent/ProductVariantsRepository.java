package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantsRepository extends JpaRepository<ProductVariants,String> {
}
