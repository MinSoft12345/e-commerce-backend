package com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp;

import com.admindashboard.e_commerce.e_commerce.model.order.orderComp.Order;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {
    private String orderItemId;
    private Order order;
    private Product product;
    private Integer quantity;
    private BigDecimal priceAtOrder;
}
