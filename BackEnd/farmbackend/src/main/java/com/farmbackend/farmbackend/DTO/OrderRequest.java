package com.farmbackend.farmbackend.DTO;

import com.farmbackend.farmbackend.Entities.Orders;


public class OrderRequest {
    private Orders orders;
    private int farmerId; 
    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    // Getter và Setter cho farmerId
    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

}
