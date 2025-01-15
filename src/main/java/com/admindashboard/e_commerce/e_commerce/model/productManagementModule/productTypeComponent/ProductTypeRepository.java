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

    @Query(value = "select prod.id ,category.type_name , prod.product_name ,\n" +
            "prod.prod_current_price , prod.stock_amount , prod.prod_status , \n" +
            "to_char(prod.created_at::date,'dd-Mon-YYYY') \n" +
            "from ec_product as prod\n" +
            "left join ec_product_type as category on prod.category_id = category.id " +
            "order by prod.created_at desc", nativeQuery = true)
    List<Object[]> getAllProducts();

    @Query(value = "select prodVariant.id ,prod.product_name , prodVariant.product_variant_name ,\n" +
            "            prodVariant.curr_price , prodVariant.product_quantity , prodVariant.variant_status ,\n" +
            "            to_char(prodVariant.created_at::date,'dd-Mon-YYYY') create_date\n" +
            "            from ec_product_variants as prodVariant\n" +
            "            left join ec_product as prod on prodVariant.product_id = prod.id\n" +
            "            order by prodVariant.created_at desc", nativeQuery = true)
    List<Object[]> getAllProductVariants();
}
