package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImage;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImageRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.admindashboard.e_commerce.e_commerce.allenum.ProductStatus.INACTIVE;

@Service
public class ProductService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductImageRepository productImageRepository;


    public ProductResponse addProduct(ProductRequest request)
    {
        Optional<User> user1 = userRepository.findByUserName(request.getUserName());
        if (user1.isEmpty()) {
            throw new EntityNotFoundException("Creator user name is not found.");
        }

        Optional<ProductType> productType1 = productTypeRepository.findById(request.getCategoryId());
        if (productType1.isEmpty()) {
            throw new EntityNotFoundException("Category or product type name is not found.");
        }

        ProductImage productThumbnail = productImageRepository.findById(request.getProductThumbnailId()).orElseThrow(() -> new RuntimeException("Image thumbnail is not found"));


        User creator = user1.get();
        Boolean isActive = !request.getProdStatus().equals(INACTIVE);

        var product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .category(productType1.get())
                .prodStatus(request.getProdStatus())
                .basePrice(request.getBasePrice())
                .tenantId(creator.getTenantId())
                .sku(request.getSku())
                .score(request.getScore())
                .metaTagTitle(request.getMetaTagTitle())
                .metaTagDescription(request.getMetaTagDescription())
                .metaTagKeyword(request.getMetaTagKeyword())
                .isActive(isActive)
                .createdBy(creator)
                .updatedBy(creator)
                .productThumbnailUrl(productThumbnail.getImageUrl())
                .build();
        product = productRepository.save(product);

        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .categoryName(productType1.get().getTypeName())
                .prodStatus(product.getProdStatus())
                .basePrice(product.getBasePrice())
                .sku(product.getSku())
                .score(product.getScore())
                .metaTagTitle(product.getMetaTagTitle())
                .metaTagDescription(product.getMetaTagDescription())
                .metaTagKeyword(product.getMetaTagKeyword())
                .isActive(product.getIsActive())
                .thumbNailUrl(productThumbnail.getImageUrl())
                .build();
    }
}
