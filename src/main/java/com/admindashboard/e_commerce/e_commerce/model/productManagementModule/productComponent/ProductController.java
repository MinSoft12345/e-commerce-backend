package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.dto.ProductTypeDto;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(ProductRequest request) {
        try {
            ProductResponse response = productService.addProduct(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prod-list")
    public ResponseEntity<?> getProductList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(productService.getProductList(pageable));
        } catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("internal server error.",ResponseType.E),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-id/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            ProductResponse productResponse = productService.getProductById(productId);
            return ResponseEntity.ok(productResponse);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new MessageResponse("Internal server error.", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest request) {
        try {
            return ResponseEntity.ok(productService.updateProduct(request));
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new MessageResponse("Internal server error.", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
