package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductTypeRequest;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO.ProductTypeResponse;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    public ProductTypeResponse addProductType(ProductTypeRequest request)
    {
        Optional<User> user1 = userRepository.findByUserName(request.getUserName());
        if (user1.isEmpty()) {
            throw new EntityNotFoundException("Creator user name is not found.");
        }
        var productType = ProductType.builder()
                .typeName(request.getTypeName())
                .description(request.getDescription())
                .tenantId(user1.get().getTenantId())
                .build();
        productType = productTypeRepository.save(productType);
        return ProductTypeResponse.builder()
                .productTypeId(productType.getId())
                .typeName(productType.getTypeName())
                .description(productType.getDescription())
                .build();
    }

    public ProductTypeResponse getProductType(String id)
    {
        Optional<ProductType>productType1 = productTypeRepository.findById(id);
        if (productType1.isEmpty()) {
            throw new EntityNotFoundException("Creator user name is not found.");
        }
        ProductType productType = productType1.get();

        return ProductTypeResponse.builder()
                .typeName(productType.getTypeName())
                .description(productType.getDescription())
                .productTypeId(productType.getId())
                .build();
    }
}
