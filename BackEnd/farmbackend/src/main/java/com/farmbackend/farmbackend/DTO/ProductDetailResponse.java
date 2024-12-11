package com.farmbackend.farmbackend.DTO;

import java.util.List;

import com.farmbackend.farmbackend.Entities.Comment;
import com.farmbackend.farmbackend.Entities.Product;

public class ProductDetailResponse {
    private Product product;
    private List<CommentResponse> comments;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

   
}
