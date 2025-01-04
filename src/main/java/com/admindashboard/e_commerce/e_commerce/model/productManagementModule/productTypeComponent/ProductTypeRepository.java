package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent;

import com.admindashboard.e_commerce.e_commerce.dto.ProductTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,String> {
    Optional<ProductType>findByTypeName(String typeName);

    @Query(value = "SELECT id, type_name FROM public.ec_product_type", nativeQuery = true)
    List<Object[]> findAllProductTypesRaw();
}
