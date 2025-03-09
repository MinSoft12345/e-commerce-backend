package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO;

import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryRequest {
    private String id;
    private String name;
    private String description;
    private Boolean isActive;
    private String code;

    public ProductCategoryRequest(ProductCategory productCategory) {
        this.setId(productCategory.getId());
        this.setCode(productCategory.getCode());
        this.setName(productCategory.getName());
        this.setIsActive(productCategory.getIsActive());
        this.setDescription(productCategory.getDescription());
    }
}
