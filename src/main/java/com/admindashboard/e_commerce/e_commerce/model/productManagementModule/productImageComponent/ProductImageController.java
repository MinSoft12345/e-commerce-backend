package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent;

import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductImageRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/product-image")
public class ProductImageController {

    private final ProductImageService productImageService;

    public ProductImageController(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ProductImageResponse> addImage(@RequestPart MultipartFile file,@RequestPart String productImageRequest) {
        try {
            ProductImageRequest request = convertToProductImageRequest(productImageRequest);
            ProductImageResponse savedImage = productImageService.addImage(request,file);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductImageResponse> viewImage(@PathVariable String id) {
        try{
            ProductImageResponse productImage = productImageService.getImage(id);
            System.out.println(productImage);
            return ResponseEntity.ok(productImage);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }

    private ProductImageRequest convertToProductImageRequest(String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(request,ProductImageRequest.class);
    }
}
