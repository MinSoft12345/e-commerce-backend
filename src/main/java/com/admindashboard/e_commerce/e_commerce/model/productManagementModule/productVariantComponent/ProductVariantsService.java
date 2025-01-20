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


    public ProductVariantsResponse updateProductVariant(ProductVariantsRequest request) {
        // Retrieve the updater user
        User updater = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("Updater user name is not found."));

        // Retrieve the product variant to be updated
        ProductVariants productVariants = productVariantsRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product ID not found."));

        // Update fields from the request
        if (request.getVariantName() != null) {
            productVariants.setVariantName(request.getVariantName());
        }
        if (request.getVariantDescription() != null) {
            productVariants.setVariantDescription(request.getVariantDescription());
        }
        if (request.getWeight() != null) {
            productVariants.setWeight(request.getWeight());
        }
        if (request.getHeight() != null) {
            productVariants.setHeight(request.getHeight());
        }
        if (request.getWidth() != null) {
            productVariants.setWidth(request.getWidth());
        }
        if (request.getLength() != null) {
            productVariants.setLength(request.getLength());
        }
        if (request.getBarCode() != null) {
            productVariants.setBarCode(request.getBarCode());
        }
        if (request.getQuantity() != null) {
            productVariants.setQuantity(request.getQuantity());
        }
        if (request.getBasePrice() != null) {
            productVariants.setBasePrice(request.getBasePrice());
        }
        if (request.getCurrPrice() != null) {
            productVariants.setBasePrice(request.getCurrPrice());
        }
        if (request.getVariantStatus() != null) {
            productVariants.setVariantStatus(request.getVariantStatus());
        }
        if (request.getColorCode() != null) {
            productVariants.setColorCode(request.getColorCode());
        }

        if (request.getProductScore() != null) {
            productVariants.setProductScore(request.getProductScore());
        }

        if (request.getImageId() != null) {
            ProductImage productImage = productImageRepository.findById(request.getImageId())
                    .orElseThrow(() -> new RuntimeException("Image thumbnail is not found."));
            productVariants.setProductImage(productImage);
        }
        productVariants = productVariantsRepository.save(productVariants);

        return mapToProductVariantResponse(productVariants);
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
