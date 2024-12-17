package com.farmbackend.farmbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
     List<Orders> findByFarmer(Farmer farmer);
}