package com.farmbackend.farmbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.farmbackend.farmbackend.Entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByProduct_ProductId(int productId);

    @Query("SELECT c.content, c.createDate, c.supplier.supplierId, c.farmer.farmerId, " +
    "CASE WHEN c.supplier IS NOT NULL THEN c.supplier.account.firstName ELSE c.farmer.account.firstName END AS firstName, " +
    "CASE WHEN c.supplier IS NOT NULL THEN c.supplier.account.lastName ELSE c.farmer.account.lastName END AS lastName " +
    "FROM Comment c " +
    "LEFT JOIN c.supplier s " +
    "LEFT JOIN s.account sa " +
    "LEFT JOIN c.farmer f " +
    "LEFT JOIN f.account fa " +
    "WHERE c.product.productId = :productId " +
    "ORDER BY c.createDate DESC")
List<Object> findCommentsByProductId(@Param("productId") int productId);
}
