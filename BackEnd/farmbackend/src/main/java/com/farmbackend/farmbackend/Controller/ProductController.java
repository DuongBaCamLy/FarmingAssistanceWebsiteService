package com.farmbackend.farmbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.CommentRequest;
import com.farmbackend.farmbackend.DTO.ProductDetailResponse;
import com.farmbackend.farmbackend.Entities.Comment;
import com.farmbackend.farmbackend.Entities.Product;
import com.farmbackend.farmbackend.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @GetMapping
     public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> admins = productService.getAllProducts();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    } 

    @GetMapping("/{producId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable int producId) {
        ProductDetailResponse product = productService.getProductDetail(producId);
       return new ResponseEntity<>(product, HttpStatus.OK);
   } 

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<Product>> getAllProductsBySupplierId(@PathVariable int supplierId) {
       List<Product> admins = productService.getAllProductsBySupplier(supplierId);
       return new ResponseEntity<>(admins, HttpStatus.OK);
   } 

    @PostMapping("/supplier/{supplierId}")
    public Product createProduct(@RequestBody Product product, @PathVariable int supplierId) {
        return productService.createProduct(product, supplierId);
    }

    @PostMapping("add-comment/{productId}/comment")
    public Comment createComment(@RequestBody CommentRequest comment, @PathVariable int productId) {
        return productService.createComment(comment, productId);
    }
}
