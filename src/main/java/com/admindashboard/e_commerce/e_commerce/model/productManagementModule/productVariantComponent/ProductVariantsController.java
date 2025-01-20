package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsResponse;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-variant")
@RequiredArgsConstructor
public class ProductVariantsController {

    private final ProductVariantsService productVariantsService;

    @PostMapping("/add")
    public ResponseEntity<?> addProductVariant( ProductVariantsRequest request) {
        try {
            ProductVariantsResponse response = productVariantsService.addProductVariant(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new MessageResponse(e.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new MessageResponse("An error occurred while adding product variant", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/prod-variants-list")
    public ResponseEntity<?> getProductVariantsList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            return ResponseEntity.ok(productVariantsService.getProductVariantList(pageable));
        } catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("internal server error.",ResponseType.E),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-id/{productVariantId}")
    public ResponseEntity<?> getProductVariantsById(@PathVariable String productVariantId) {
        try{
            return ResponseEntity.ok(productVariantsService.getProductVariantById(productVariantId));
        } catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("internal server error.",ResponseType.E),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProductVariants(@RequestBody ProductVariantsRequest request) {
        try{
            return ResponseEntity.ok(productVariantsService.updateProductVariant(request));
        } catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("internal server error.",ResponseType.E),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
