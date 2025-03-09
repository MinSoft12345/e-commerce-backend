package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.controller;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductCategoryRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductTypeResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.service.ProductCategorySercvice;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import com.admindashboard.e_commerce.e_commerce.utils.Global;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/category")
public class CategoryController {

    private static final Logger logger = LogManager.getLogger(Global.PRODUCT_LOG);

    @Autowired
    ProductCategorySercvice productCategorySercvice;


    @PostMapping("/add")
    public ResponseEntity<?> addProductType(@RequestBody ProductCategoryRequest request) {
        try {
            ProductCategoryRequest productCategoryRequest = productCategorySercvice.addProductCategory(request);
            logger.info("Product Category has been created with this code :"+productCategoryRequest.getCode());
            return new ResponseEntity<>(productCategoryRequest, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("An error occurred while adding product type", e);
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product type", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
