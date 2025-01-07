package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest request) {
        try {
            ProductResponse response = productService.addProduct(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
