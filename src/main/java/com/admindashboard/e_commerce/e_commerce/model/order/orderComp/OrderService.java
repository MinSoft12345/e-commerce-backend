package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.dto.OrderStatus;
import com.admindashboard.e_commerce.e_commerce.model.addressComp.SubDistrict;
import com.admindashboard.e_commerce.e_commerce.model.addressComp.SubDistrictRepository;
import com.admindashboard.e_commerce.e_commerce.model.manualCustomer.ManualCustomer;
import com.admindashboard.e_commerce.e_commerce.model.manualCustomer.ManualCustomerRepository;
import com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp.OrderItem;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManualCustomerRepository manualCustomerRepository;

    @Autowired
    private SubDistrictRepository subDistrictRepository;

    @Transactional
    public OrderDto addOrder(@RequestBody OrderDto orderDto){

        /*
        manualCustomerId
        subDistrictId
        orderDate
        adminNote
        customerNote
        deliveryMethod
        addressLine
        {
         productId :
         quantity :
        } (list)
        tax ,
        discount
        deliveryFee

        * */

//        ManualCustomer manualCustomer = manualCustomerRepository.findById(orderDto.getManualCustomerId()).orElseThrow();
//        SubDistrict subDistrict = subDistrictRepository.findById(orderDto.getSubDistrictId()).orElseThrow();
        ManualCustomer manualCustomer = manualCustomerRepository.findById(orderDto.getManualCustomerId())
                .orElseThrow(() -> new RuntimeException("Manual Customer not found"));

        SubDistrict subDistrict = subDistrictRepository.findById(orderDto.getSubDistrictId())
                .orElseThrow(() -> new RuntimeException("SubDistrict not found"));

        if (orderDto.getProductIdList() == null || orderDto.getProductIdList().isEmpty()) {
            throw new RuntimeException("Product list cannot be empty");
        }

        BigDecimal subtotalAmount = new BigDecimal("0.0");

        var order = Order.builder()
                .orderDate(orderDto.getOrderDate())
                .manualCustomer(manualCustomer)
                .adminNotes(orderDto.getAdminNotes())
                .customerNotes(orderDto.getCustomerNotes())
                .subDistrict(subDistrict)
                .status(OrderStatus.PENDING)
                .shippingMethod(orderDto.getDeliveryMethod())
                .paymentMethod(orderDto.getPaymentMethod())
                .addressLine(orderDto.getAddressLine())
                .shippingFee(orderDto.getShippingFee())
                .taxAmount(orderDto.getTaxAmount())
                .build();

        List<OrderItem> itemList = new ArrayList<>();

        for(ProductIdAndQuantity productIdAndQuantity : orderDto.getProductIdList()){

            Product product = productRepository.findById(productIdAndQuantity.getProductId()).orElseThrow();
            Integer quantity = productIdAndQuantity.getQuantity();
            BigDecimal priceAt = BigDecimal.valueOf(((long) product.getBasePrice() * quantity));
            itemList.add(OrderItem.builder()
                            .order(order)
                            .priceAtOrder(priceAt)
                            .product(product)
                            .quantity(quantity)

                    .build());

            subtotalAmount = subtotalAmount.add(priceAt);
        }
        order.setTotalAmount(subtotalAmount);
        order.setSubtotal(subtotalAmount);
        order.setOrderItems(itemList);

        order = orderRepository.save(order);

        return OrderDto.builder()
                .orderDate(order.getOrderDate())
                .manualCustomerId(order.getManualCustomer().getId())
                .adminNotes(order.getAdminNotes())
                .customerNotes(order.getCustomerNotes())
                .divisionName(order.getSubDistrict().getDistrict().getDivision().getDivisionName())
                .districtName(order.getSubDistrict().getDistrict().getDistrictName())
                .subDistrictName(order.getSubDistrict().getSubDistrictName())
                .subTotal(order.getTotalAmount())
                .currentStatus(String.valueOf(order.getStatus()))
                .deliveryMethod(order.getShippingMethod())
                .paymentMethod(order.getPaymentMethod())
                .addressLine(order.getAddressLine())
                .itemList(order.getOrderItems())
                .shippingFee(order.getShippingFee())
                .taxAmount(order.getTaxAmount())
                .build();
    }

    private OrderDto convertToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .deliveryMethod(order.getShippingMethod())
                .subTotal(order.getTotalAmount())
                .adminNotes(order.getAdminNotes())
                .customerNotes(order.getCustomerNotes())
                .addressLine(order.getAddressLine())
                .subDistrictName(order.getSubDistrict().getSubDistrictName())
                .districtName(order.getSubDistrict().getDistrict().getDistrictName())
                .divisionName(order.getSubDistrict().getDistrict().getDivision().getDivisionName())
                .currentStatus(String.valueOf(order.getStatus()))
                .paymentMethod(order.getPaymentMethod())
                .manualCustomerName(order.getManualCustomer().getName())
                .manualCustomerPhone(order.getManualCustomer().getPhoneNumber())
                .itemList(order.getOrderItems())
                .build();
    }

    public Page<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepository.findAllOrders(pageable).map(this::convertToOrderDto);
    }


    public Optional<OrderDto> getOrderById(String id) {
        return orderRepository.findById(id).map(this::convertToOrderDto);
    }

    public OrderDto updateOrder(String id, OrderDto orderDto) {
        return orderRepository.findById(id).map(order -> {
            Order updatedOrder = Order.builder()
                    .id(order.getId())
                    .orderDate(order.getOrderDate())
                    .status(orderDto.getCurrentStatus() != null ?
                            OrderStatus.valueOf(orderDto.getCurrentStatus()) : order.getStatus())
                    .shippingMethod(orderDto.getDeliveryMethod() != null ?
                            orderDto.getDeliveryMethod() : order.getShippingMethod())
                    .orderItems(orderDto.getItemList() != null ?
                            orderDto.getItemList() : order.getOrderItems())
                    .subtotal(orderDto.getSubTotal() != null ?
                            orderDto.getSubTotal() : order.getSubtotal())
                    .shippingFee(order.getShippingFee())
                    .taxAmount(order.getTaxAmount())
                    .discountAmount(order.getDiscountAmount())
                    .totalAmount(order.getTotalAmount())
                    .adminNotes(orderDto.getAdminNotes() != null ?
                            orderDto.getAdminNotes() : order.getAdminNotes())
                    .customerNotes(orderDto.getCustomerNotes() != null ?
                            orderDto.getCustomerNotes() : order.getCustomerNotes())
                    .paymentMethod(orderDto.getPaymentMethod() != null ?
                            orderDto.getPaymentMethod() : order.getPaymentMethod())
                    .addressLine(orderDto.getAddressLine() != null ?
                            orderDto.getAddressLine() : order.getAddressLine())
                    .subDistrict(order.getSubDistrict())
                    .manualCustomer(order.getManualCustomer())
                    .build();

            return convertToOrderDto(orderRepository.save(updatedOrder));
        }).orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
    }



    // Search Orders using Date Range, Order Pay Type, Current State Type
//    public List<OrderDto> searchOrders(LocalDateTime startDate, LocalDateTime endDate, String orderPayType, String currentStatus) {
//        List<Order> orders;
//        if (startDate != null && endDate != null) {
//            orders = orderRepository.findByLastUpdatedDateTimeBetween(startDate, endDate);
//        } else if (orderPayType != null) {
//            orders = orderRepository.findByOrderPayType(orderPayType);
//        } else if (currentStatus != null) {
//            orders = orderRepository.findByCurrentStatus(currentStatus);
//        } else {
//            orders = orderRepository.findAll();
//        }
//        return orders.stream().map(this::convertToOrderDto).collect(Collectors.toList());
//    }
// Get Order History for a specific customer
//    public List<OrderDto> getOrderHistoryForCustomer(String customerId) {
//        return orderRepository.findByCustomerId(customerId).stream().map(this::convertToOrderDto).collect(Collectors.toList());
//    }

}
