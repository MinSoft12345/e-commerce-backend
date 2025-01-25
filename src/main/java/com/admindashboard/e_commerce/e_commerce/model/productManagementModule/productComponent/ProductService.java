package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.dto.ProductTypeDto;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.PaginatedResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImage;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImageRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .vat(request.getVat())
                .discount(request.getDiscount())
                .width(request.getWidth())
                .height(request.getHeight())
                .length(request.getLength())
                .weight(request.getWeight())
                .prodCurrPrice(request.getProdCurrPrice())
                .stockAmount(request.getStockAmount())
                .barcode(request.getBarcode())
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

        return mapToProductResponse(product);
    }

    public PaginatedResponse<ProductResponse> getProductList(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> products = productPage.stream()
                .map(this::mapToProductResponse)
                .toList();

        return new PaginatedResponse<>(
                products,
                productPage.getTotalPages(),
                productPage.getTotalElements(),
                productPage.getNumber(),
                productPage.getSize()
        );
    }


    public ProductResponse getProductById(String productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product is not found."));
        return mapToProductResponse(product);
    }


    public ProductResponse updateProduct(ProductRequest request)
    {

        User updater = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("Updater user name is not found."));

        ProductType productType = productTypeRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category or product type is not found."));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product ID not found."));

        if (request.getProductThumbnailId() != null) {
            ProductImage productThumbnail = productImageRepository.findById(request.getProductThumbnailId())
                    .orElseThrow(() -> new RuntimeException("Image thumbnail is not found."));
            product.setProductThumbnailUrl(productThumbnail.getImageUrl());
        }

        updateProductFields(product, request, updater, productType);

        product = productRepository.save(product);

        return mapToProductResponse(product);
    }

    private void updateProductFields(Product product, ProductRequest request, User updater, ProductType productType) {
        Boolean isActive = request.getProdStatus() != null && !request.getProdStatus().equals(INACTIVE);

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }
        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }
        if (productType != null) {
            product.setCategory(productType);
        }
        if (request.getProdStatus() != null) {
            product.setProdStatus(request.getProdStatus());
        }
        if (request.getBasePrice() != null) {
            product.setBasePrice(request.getBasePrice());
        }
        if (request.getVat() != null) {
            product.setVat(request.getVat());
        }
        if (request.getDiscount() != null) {
            product.setDiscount(request.getDiscount());
        }
        if (request.getWidth() != null) {
            product.setWidth(request.getWidth());
        }
        if (request.getHeight() != null) {
            product.setHeight(request.getHeight());
        }
        if (request.getLength() != null) {
            product.setLength(request.getLength());
        }
        if (request.getWeight() != null) {
            product.setWeight(request.getWeight());
        }
        if (request.getProdCurrPrice() != null) {
            product.setProdCurrPrice(request.getProdCurrPrice());
        }
        if (request.getStockAmount() != null) {
            product.setStockAmount(request.getStockAmount());
        }
        if (request.getBarcode() != null) {
            product.setBarcode(request.getBarcode());
        }
        if (request.getSku() != null) {
            product.setSku(request.getSku());
        }
        if (request.getScore() != null) {
            product.setScore(request.getScore());
        }
        if (request.getMetaTagTitle() != null) {
            product.setMetaTagTitle(request.getMetaTagTitle());
        }
        if (request.getMetaTagDescription() != null) {
            product.setMetaTagDescription(request.getMetaTagDescription());
        }
        if (request.getMetaTagKeyword() != null) {
            product.setMetaTagKeyword(request.getMetaTagKeyword());
        }

        if (request.getProdStatus() != null) {
            product.setIsActive(isActive);
        }

        product.setUpdatedBy(updater);
    }


    private ProductResponse mapToProductResponse(Product product)
    {
        return ProductResponse.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .categoryName(product.getCategory().getTypeName())
                .prodStatus(product.getProdStatus())
                .basePrice(product.getBasePrice())
                .vat(product.getVat())
                .discount(product.getDiscount())
                .width(product.getWidth())
                .height(product.getHeight())
                .length(product.getLength())
                .weight(product.getWeight())
                .prodCurrPrice(product.getProdCurrPrice())
                .stockAmount(product.getStockAmount())
                .barcode(product.getBarcode())
                .sku(product.getSku())
                .score(product.getScore())
                .metaTagTitle(product.getMetaTagTitle())
                .metaTagDescription(product.getMetaTagDescription())
                .metaTagKeyword(product.getMetaTagKeyword())
                .isActive(product.getIsActive())
                .thumbNailUrl(product.getProductThumbnailUrl())
                .build();
    }
}
