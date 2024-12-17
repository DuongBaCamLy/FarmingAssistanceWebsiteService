package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.DTO.OrderRequest;
import com.farmbackend.farmbackend.DTO.OrderResponse;
import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Entities.Orders;
import com.farmbackend.farmbackend.Repository.FarmerRepository;
import com.farmbackend.farmbackend.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FarmerRepository farmerRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(int order_id) {
        return orderRepository.findById(order_id).orElse(null);
    }

    @Transactional
    public Orders createOrder(Orders orders, int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found"));

        orders.setFarmer(farmer);

        return orderRepository.save(orders);
    }

    public void deleteOrder(int order_id) {
        orderRepository.deleteById(order_id);
    }

    @Transactional
    public Orders createoOrders(OrderRequest params, int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found"));

        Orders orders = params.getOrders();

        orders.setFarmer(farmer);

        orderRepository.save(orders);

        return orders;
    }

    @Transactional
    public Orders updateOrder(int order_id, OrderRequest params) {
        // Tìm Order hiện tại từ cơ sở dữ liệu
        Orders existingOrder = orderRepository.findById(order_id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

// Cập nhật thông tin Order
        Orders updatedOrders = params.getOrders();
        existingOrder.setTotalprice(updatedOrders.getTotalprice());


// Lưu Order đã cập nhật
        return orderRepository.save(existingOrder);
    }

    public List<Orders> getAllOrdersByFarmerId(int farmerId) {
        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() -> new IllegalArgumentException("Farmer not found with ID: " + farmerId));

        return orderRepository.findByFarmer(farmer);
    }

    public OrderResponse getOrderDetail(int order_id) {
        OrderResponse response = new OrderResponse();
        Optional<Orders> order = orderRepository.findById(order_id);

        response.setOrders(order.get());
        
        return response;
    }
}
