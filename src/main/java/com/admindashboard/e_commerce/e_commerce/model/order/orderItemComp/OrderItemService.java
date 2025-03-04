package com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp;

import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderItem addOrderItem(OrderItemDto orderItemDto){

        var orderItem = OrderItem.builder()
                .order(orderItemDto.getOrder())
                .priceAtOrder(orderItemDto.getPriceAtOrder())
                .product(orderItemDto.getProduct())
                .quantity(orderItemDto.getQuantity())
                .build();

        return orderItemRepository.save(orderItem);
    }
}
