package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
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
public class ProductVariantsService {

    @Autowired
    ProductVariantsRepository productVariantsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    public ProductVariantsResponse addProductVariant(ProductVariantsRequest request)
    {
        Optional<User> user1 = userRepository.findByUserName(request.getUserName());
        if (user1.isEmpty()) {
            throw new EntityNotFoundException("Creator user name is not found.");
        }
        User user = user1.get();
        ProductImage image = productImageRepository.findById(request.getImageId()).orElseThrow(() -> new RuntimeException("Image thumbnail is not found"));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product is not found"));

        ProductVariants productVariants = ProductVariants.builder()
                .variantName(request.getVariantName())
                .variantDescription(request.getVariantDescription())
                .tenantId(user.getTenantId())
                .weight(request.getWeight())
                .height(request.getHeight())
                .width(request.getWidth())
                .length(request.getLength())
                .barCode(request.getBarCode())
                .quantity(request.getQuantity())
                .basePrice(request.getBasePrice())
                .currPrice(request.getCurrPrice())
                .variantStatus(request.getVariantStatus())
//                .colorCode(request.getColorCode())
                .productScore(request.getProductScore() != null ? request.getProductScore() : 0.0)
                .productImage(image)
                .product(product)
                .sellCount(0)
                .reviewCount(0)
                .build();

       productVariants =  productVariantsRepository.save(productVariants);
//       System.out.println(productVariants);
//       product.getProductVariantsList().add(productVariants);
//       productRepository.save(product);

        return ProductVariantsResponse.builder()
                .variantId(productVariants.getId())
                .productName( productVariants.getProduct().getProductName())
                .variantName( productVariants.getVariantName())
            .variantDescription( productVariants.getVariantDescription())
            .weight( productVariants.getWeight())
            .height( productVariants.getHeight())
            .width( productVariants.getWidth())
            .length( productVariants.getLength())
            .barCode( productVariants.getBarCode())
            .quantity( productVariants.getQuantity())
            .currPrice(productVariants.getCurrPrice())
            .basePrice( productVariants.getBasePrice())
            .variantStatus(productVariants.getVariantStatus())
//            .colorCode( productVariants.getColorCode())
            .productScore( productVariants.getProductScore() != null ?  productVariants.getProductScore() : 0.0)
            .sellCount(0)
            .reviewCount(0)
                .imageUrl(image.getImageUrl())
            .build();
    }

    public List<ProductVariantsResponse> getProductVariantList(Pageable pageable)
    {
        Page<ProductVariants> productPage = productVariantsRepository.findAll(pageable);

        return productPage.stream().map(this:: mapToProductVariantResponse).toList();
    }

    public ProductVariantsResponse getProductVariantById(String productVariantId)
    {
        ProductVariants productVariants = productVariantsRepository.findById(productVariantId).orElseThrow(()-> new EntityNotFoundException("Product is not found."));
        return mapToProductVariantResponse(productVariants);
    }

    @Transactional
    public ProductVariantsResponse updateProductVariant(ProductVariantsRequest request) {

    User updater = userRepository.findByUserName(request.getUserName())
            .orElseThrow(() -> new EntityNotFoundException("Updater user name is not found."));

    ProductVariants productVariants = productVariantsRepository.findById(request.getProductId())
            .orElseThrow(() -> new EntityNotFoundException("Product ID not found."));

    updateProductVariantFields(productVariants, request);

    productVariants = productVariantsRepository.save(productVariants);
    return mapToProductVariantResponse(productVariants);
}

    private void updateProductVariantFields(ProductVariants productVariants, ProductVariantsRequest request) {
        Optional.ofNullable(request.getVariantName()).ifPresent(productVariants::setVariantName);
        Optional.ofNullable(request.getVariantDescription()).ifPresent(productVariants::setVariantDescription);
        Optional.ofNullable(request.getWeight()).ifPresent(productVariants::setWeight);
        Optional.ofNullable(request.getHeight()).ifPresent(productVariants::setHeight);
        Optional.ofNullable(request.getWidth()).ifPresent(productVariants::setWidth);
        Optional.ofNullable(request.getLength()).ifPresent(productVariants::setLength);
        Optional.ofNullable(request.getBarCode()).ifPresent(productVariants::setBarCode);
        Optional.ofNullable(request.getQuantity()).ifPresent(productVariants::setQuantity);
        Optional.ofNullable(request.getBasePrice()).ifPresent(productVariants::setBasePrice);
        Optional.ofNullable(request.getCurrPrice()).ifPresent(productVariants::setBasePrice);
        Optional.ofNullable(request.getVariantStatus()).ifPresent(productVariants::setVariantStatus);
        Optional.ofNullable(request.getColorCode()).ifPresent(productVariants::setColorCode);
        Optional.ofNullable(request.getProductScore()).ifPresent(productVariants::setProductScore);

        if (request.getImageId() != null) {
            ProductImage productImage = productImageRepository.findById(request.getImageId())
                    .orElseThrow(() -> new RuntimeException("Image thumbnail is not found."));
            productVariants.setProductImage(productImage);
        }
    }


    private ProductVariantsResponse mapToProductVariantResponse(ProductVariants productVariants) {
        return ProductVariantsResponse.builder()
                .variantId(productVariants.getId())
                .variantName(productVariants.getVariantName()) // Assuming this exists
                .variantDescription(productVariants.getVariantDescription())
                .weight(productVariants.getWeight())
                .height(productVariants.getHeight())
                .width(productVariants.getWidth())
                .length(productVariants.getLength())
                .barCode(productVariants.getBarCode())
                .quantity(productVariants.getQuantity())
                .basePrice(productVariants.getBasePrice())
                .currPrice(productVariants.getCurrPrice())
                .variantStatus(productVariants.getVariantStatus())
                .colorCode(productVariants.getColorCode())
                .productScore(productVariants.getProductScore())
                .imageUrl(productVariants.getProductImage().getImageUrl())
                .sellCount(productVariants.getSellCount())
                .reviewCount(productVariants.getReviewCount())
                .build();
    }

}
