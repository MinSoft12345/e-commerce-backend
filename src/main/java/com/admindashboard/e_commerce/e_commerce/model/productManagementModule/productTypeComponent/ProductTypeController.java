package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent;
import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.dto.ProductTypeDto;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductTypeRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductTypeResponse;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-type")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @PostMapping("/add")
    public ResponseEntity<?> addProductType(@RequestBody ProductTypeRequest request) {
        try {
            return new ResponseEntity<>(productTypeService.addProductType(request), HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product type", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{productTypeId}")
    public ResponseEntity<?> findProductType(@PathVariable String productTypeId) {
        try {
            ProductTypeResponse response = productTypeService.getProductType(productTypeId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product type", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/prod-type-list")
    public List<ProductTypeDto> getProductTypes() {
        return productTypeService.getAllProductTypess();
    }
}
