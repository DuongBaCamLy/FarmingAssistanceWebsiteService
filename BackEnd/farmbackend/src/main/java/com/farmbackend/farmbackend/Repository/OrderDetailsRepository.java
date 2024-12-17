package com.farmbackend.farmbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.OrderDetail;
import com.farmbackend.farmbackend.Entities.Orders;
import com.farmbackend.farmbackend.Entities.Product;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {

    // Find all OrderDetails by a specific Order
    List<OrderDetail> findByOrder(Orders order);

    // Find all OrderDetails by a specific Product
    List<OrderDetail> findByProduct(Product product);
}
