package com.farmbackend.farmbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Product;
import com.farmbackend.farmbackend.Entities.Supplier;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findBySupplier(Supplier supplier);

    @Query("SELECT p.productId, p.name, p.price, p.status, p.urlImg FROM Product p WHERE p.supplier.supplierId = :supplierId")
    List<Object[]> findAllBySupplierId(@Param("supplierId") int supplierId);

    @Query("SELECT p.productId, p.name, p.price, p.status, p.urlImg FROM Product p")
    List<Object[]> findAllProducts();

    
    @Query("SELECT p.productId, p.name, p.price, p.status, p.urlImg FROM Product p WHERE p.productId = :productId")
    Object findOneObject(@Param("productId") int productId);
}
