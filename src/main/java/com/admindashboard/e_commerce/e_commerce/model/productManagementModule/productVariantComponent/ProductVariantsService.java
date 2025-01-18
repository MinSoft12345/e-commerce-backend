package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductVariantsResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImage;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
