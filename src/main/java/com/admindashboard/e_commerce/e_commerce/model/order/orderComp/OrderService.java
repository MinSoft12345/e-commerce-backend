package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.dto.OrderStatus;
import com.admindashboard.e_commerce.e_commerce.model.addressComp.SubDistrict;
import com.admindashboard.e_commerce.e_commerce.model.addressComp.SubDistrictRepository;
import com.admindashboard.e_commerce.e_commerce.model.manualCustomer.ManualCustomer;
import com.admindashboard.e_commerce.e_commerce.model.manualCustomer.ManualCustomerRepository;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

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

    public OrderDto addOrder(@RequestBody OrderDto orderDto){

        Product product = productRepository.findById(orderDto.getOrderId()).orElseThrow();
        ManualCustomer manualCustomer = manualCustomerRepository.findById(orderDto.getManualCustomerId()).orElseThrow();
        Double totalAmount = (double) (product.getBasePrice() * orderDto.getQuantity());
        SubDistrict subDistrict = subDistrictRepository.findById(orderDto.getSubDistrictId()).orElseThrow();

        var order = Order.builder()
                .orderDate(orderDto.getOrderDate())
                .manualCustomer(manualCustomer)
                .adminNotes(orderDto.getAdminNotes())
                .customerNotes(orderDto.getCustomerNotes())
                .subDistrict(subDistrict)
                .subtotal(BigDecimal.valueOf(totalAmount))
                .status(OrderStatus.PENDING)
                .shippingMethod(orderDto.getDeliveryMethod())
                .paymentMethod(orderDto.getPaymentMethod())
                .addressLine(orderDto.getAddressLine())
                .build();

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
                .build();
    }
}
