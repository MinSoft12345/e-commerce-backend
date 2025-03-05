package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.dto.ShippingMethod;
import com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private String id;
    private String orderId;
    private Integer trackingNumber;

    private ShippingMethod deliveryMethod;

    private String currentStatus;
    private LocalDateTime lastUpdatedDateTime;
    private LocalDateTime orderDate;
    private String shippingAddress;
    private OffsetDateTime estimatedDeliveryDate;
    private String carrierDetails;
    private Boolean isDelivered;

    private List<ProductIdAndQuantity> productIdList;//product id and quantity list

    private String subDistrictId;
    private String manualCustomerId;
    private Integer quantity = 0;
    private String adminNotes;
    private String customerNotes;

    private String paymentMethod;

    private String addressLine;

    private String manualCustomerName;

    private String manualCustomerPhone;

    private String divisionName;

    private String districtName;

    private String subDistrictName;

    private BigDecimal subTotal;

    private List<OrderItem>itemList = new ArrayList<>();

    private BigDecimal shippingFee;
    private BigDecimal taxAmount;
}
