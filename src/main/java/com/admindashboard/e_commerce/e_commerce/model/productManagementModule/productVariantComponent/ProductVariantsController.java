package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.PaginatedResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
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

    private final ProductVariantsRepository variantRepository;

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
    public ResponseEntity<?> getProductVariantsList(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            PaginatedResponse<ProductVariantsResponse> response = productVariantsService.getProductVariantList(pageable);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return new ResponseEntity<>(
                    new MessageResponse("Internal server error.", ResponseType.E),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
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
    public ResponseEntity<?> updateProductVariants(ProductVariantsRequest request) {
        try {
            ProductVariantsResponse response = productVariantsService.updateProductVariant(request);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new MessageResponse(ex.getMessage(), ResponseType.E), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            // Log the exception
            ex.printStackTrace();
            return new ResponseEntity<>(new MessageResponse("Internal server error.", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{variantID}")
    public ResponseEntity<?> deleteProduct(@PathVariable String variantID) {
        if (variantRepository.existsById(variantID)) {
            variantRepository.deleteById(variantID);
            return ResponseEntity.ok("Product Variant deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Variant not found.");
        }
    }
}
