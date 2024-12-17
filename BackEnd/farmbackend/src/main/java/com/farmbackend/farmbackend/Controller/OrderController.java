package com.farmbackend.farmbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.OrderRequest;
import com.farmbackend.farmbackend.DTO.OrderResponse;
import com.farmbackend.farmbackend.Entities.Orders;
import com.farmbackend.farmbackend.Service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public ResponseEntity<String> testOrderEndpoint() {
        return ResponseEntity.ok("Order API is working!");
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Orders>> getAllOrdersByFarmerId(@PathVariable int farmerId) {
        List<Orders> orders = ordersService.getAllOrdersByFarmerId(farmerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> getOrderDetailById(@PathVariable int order_id) {
        OrderResponse order = ordersService.getOrderDetail(order_id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/farmer/{farmerId}")
    public ResponseEntity<Orders> createOrder(@RequestBody OrderRequest params, @PathVariable int farmerId) {
        Orders savedOrders = ordersService.createoOrders(params, farmerId);    // loi 
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrders);
    }   
    
    @DeleteMapping("/{order_id}")
    public void deleteOrder(@PathVariable int order_id) {
        ordersService.deleteOrder(order_id);
    }

    @PutMapping("/{order_id}")
    public Orders updateOrder(@PathVariable int order_id, @RequestBody OrderRequest params) {
        return ordersService.updateOrder(order_id, params);
    }
}
