package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.service;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductCategoryRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductCategory;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.repo.ProductCategoryRepository;
import com.admindashboard.e_commerce.e_commerce.utils.SecurityLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategorySercvice {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductCategoryRequest addProductCategory(ProductCategoryRequest request) {

        ProductCategory productCategory = productCategoryRepository.findByCode(request.getCode());

        if (productCategory == null) {
            User user1 = SecurityLibrary.getLoggedInUser();
            productCategory = new ProductCategory();
            productCategory.setName(request.getName());
            productCategory.setDescription(request.getDescription());
            productCategory.setIsActive(Boolean.TRUE);
            productCategory.setCreatedBy(user1);
            productCategory.setCode(request.getCode());
            productCategory = productCategoryRepository.save(productCategory);
        }

        ProductCategoryRequest productCategoryRequest = new ProductCategoryRequest(productCategory);
        return productCategoryRequest;
    }
}
