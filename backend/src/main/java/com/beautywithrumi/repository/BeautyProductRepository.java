package com.beautywithrumi.repository;

import com.beautywithrumi.entity.BeautyProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for BeautyProduct entity
 * 
 * Extends MongoRepository to provide CRUD operations without implementation.
 * Demonstrates:
 * - Abstraction (defining contract without implementation)
 * - Repository pattern (separating data access logic)
 */
@Repository
public interface BeautyProductRepository extends MongoRepository<BeautyProduct, String> {

    /**
     * Find products by category
     * Spring Data MongoDB automatically implements this based on method name
     */
    List<BeautyProduct> findByCategory(String category);

    /**
     * Find products by brand (case-insensitive, partial match)
     */
    List<BeautyProduct> findByBrandContainingIgnoreCase(String brand);

    /**
     * Find products by skin type
     */
    List<BeautyProduct> findBySkinType(String skinType);

    /**
     * Find products that are in stock
     */
    List<BeautyProduct> findByInStock(Boolean inStock);
}
