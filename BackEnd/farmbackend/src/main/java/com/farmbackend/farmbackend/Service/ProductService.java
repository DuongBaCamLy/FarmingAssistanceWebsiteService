package com.farmbackend.farmbackend.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.DTO.CommentRequest;
import com.farmbackend.farmbackend.DTO.CommentResponse;
import com.farmbackend.farmbackend.DTO.ProductDetailResponse;
import com.farmbackend.farmbackend.Entities.Comment;
import com.farmbackend.farmbackend.Entities.Farmer;
import com.farmbackend.farmbackend.Entities.Product;
import com.farmbackend.farmbackend.Entities.Supplier;
import com.farmbackend.farmbackend.Repository.CommentRepository;
import com.farmbackend.farmbackend.Repository.FarmerRepository;
import com.farmbackend.farmbackend.Repository.ProductRepository;
import com.farmbackend.farmbackend.Repository.SupplierRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    public List<Product> getAllProducts() {
        List<Object[]> results = productRepository.findAllProducts();

        return results.stream()
                .map(row -> {
                    Product product = new Product();
                    product.setProductId(((Number) row[0]).intValue());
                    product.setName((String) row[1]);
                    product.setPrice((BigDecimal) row[2]);
                    product.setStatus((String) row[3]);
                    product.setUrlImg((String) row[4]);
                    return product;
                })
                .collect(Collectors.toList());
    }

    public List<Product> getAllProductsBySupplier(int supplierId) {
        List<Object[]> results = productRepository.findAllBySupplierId(supplierId);

        return results.stream()
                .map(row -> {
                    Product product = new Product();
                    product.setProductId(((Number) row[0]).intValue());
                    product.setName((String) row[1]);
                    product.setPrice((BigDecimal) row[2]);
                    product.setStatus((String) row[3]);
                    product.setUrlImg((String) row[4]);
                    return product;
                })
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product, int supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found with ID: " + supplierId));
        product.setSupplier(supplier);
        return productRepository.save(product);
    }

    public ProductDetailResponse getProductDetail(int productId) {
        ProductDetailResponse response = new ProductDetailResponse();
        Object objProduct = productRepository.findOneObject(productId);

        Product product = new Product();
        if (objProduct != null) {
            Object[] productData = (Object[]) objProduct;
            product.setProductId((Integer) productData[0]); // Assuming productId is an Integer
            product.setName((String) productData[1]);
            product.setPrice((BigDecimal) productData[2]); // Assuming price is a BigDecimal
            product.setStatus((String) productData[3]);
            product.setUrlImg((String) productData[4]);
        }

        List<Object> comments = commentRepository.findCommentsByProductId(productId);

        List<CommentResponse> responseComments = comments.stream()
        .map(obj -> {
            Object[] row = (Object[]) obj; // Cast each Object to Object[]
            return new CommentResponse(
                (String) row[0], // content
                (Date) row[1],   // createDate
                (Integer) row[2], // supplierId
                (Integer) row[3], // farmerId
                (String) row[4], // firstName
                (String) row[5]  // lastName
            );
        })
        .collect(Collectors.toList());

        response.setProduct(product);
        response.setComments(responseComments);
        return response;
    }

    public Comment createComment(CommentRequest params, int productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));

        Comment comment = new Comment();
        comment.setContent(params.getContent());
        comment.setProduct(product);

        if (params.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(params.getSupplierId())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + params.getSupplierId()));
            comment.setSupplier(supplier);
        } else if (params.getFarmerId() != null) {
            Farmer farmer = farmerRepository.findById(params.getFarmerId())
                    .orElseThrow(() -> new IllegalArgumentException("Farmer not found with ID: " + params.getFarmerId()));
            comment.setFarmer(farmer);
        } else {
            throw new IllegalArgumentException("Either supplierId or farmerId must be provided");
        }

        return commentRepository.save(comment);
    }
}
