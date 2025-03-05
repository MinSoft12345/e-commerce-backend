package com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
