package com.farmbackend.farmbackend.DTO;

import com.farmbackend.farmbackend.Entities.Orders;

public class OrderResponse {
    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

}
