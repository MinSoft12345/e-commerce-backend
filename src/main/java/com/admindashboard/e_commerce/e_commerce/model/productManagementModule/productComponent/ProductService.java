package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.dto.ProductTypeDto;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImage;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImageRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductTypeRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<ProductResponse> getProductList(Pageable pageable)
    {
        Page<Product>productPage = productRepository.findAll(pageable);

        return productPage.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProductById(String productId)
    {
        Product product = productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product is not found."));
        return mapToProductResponse(product);
    }


    public ProductResponse updateProduct(ProductRequest request)
    {
        User updater = userRepository.findByUserName(request.getUserName()).orElseThrow(
                () -> new EntityNotFoundException("updater user name is not found."));

        ProductType  productType = productTypeRepository.findById(request.getCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Category or product type name is not found."));

        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Product id not found."));

        if(request.getProductThumbnailId() != null){
            ProductImage productThumbnail = productImageRepository.findById(request.getProductThumbnailId()).orElseThrow(
                    () -> new RuntimeException("Image thumbnail is not found"));

            product.setProductThumbnailUrl(productThumbnail.getImageUrl());
        }


        Boolean isActive = !request.getProdStatus().equals(INACTIVE);

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCategory(productType);
        product.setProdStatus(request.getProdStatus());
        product.setBasePrice(request.getBasePrice());
        product.setVat(request.getVat());
        product.setDiscount(request.getDiscount());
        product.setWidth(request.getWidth());
        product.setHeight(request.getHeight());
        product.setLength(request.getLength());
        product.setWeight(request.getWeight());
        product.setProdCurrPrice(request.getProdCurrPrice());
        product.setStockAmount(request.getStockAmount());
        product.setBarcode(request.getBarcode());
        product.setTenantId(updater.getTenantId());
        product.setSku(request.getSku());
        product.setScore(request.getScore());
        product.setMetaTagTitle(request.getMetaTagTitle());
        product.setMetaTagDescription(request.getMetaTagDescription());
        product.setMetaTagKeyword(request.getMetaTagKeyword());
        product.setIsActive(isActive);
        product.setUpdatedBy(updater);

        product = productRepository.save(product);

        return mapToProductResponse(product);

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
