package com.beautywithrumi.service;

import com.beautywithrumi.dto.BeautyProductDTO;

import java.util.List;

/**
 * Service Interface for BeautyProduct operations
 * 
 * Demonstrates OOP principles:
 * - Abstraction: Defines contract without implementation details
 * - Interface segregation: Clean, focused interface
 * - Polymorphism: Can have multiple implementations
 */
public interface BeautyProductService {

    /**
     * Retrieve all beauty products
     * @return List of all products as DTOs
     */
    List<BeautyProductDTO> getAllProducts();

    /**
     * Retrieve a single product by ID
     * @param id Product ID
     * @return Product DTO
     * @throws com.beautywithrumi.exception.ResourceNotFoundException if product not found
     */
    BeautyProductDTO getProductById(String id);

    /**
     * Create a new beauty product
     * @param dto Product data
     * @return Created product DTO with generated ID
     */
    BeautyProductDTO createProduct(BeautyProductDTO dto);

    /**
     * Update an existing product completely (PUT operation)
     * @param id Product ID to update
     * @param dto New product data
     * @return Updated product DTO
     * @throws com.beautywithrumi.exception.ResourceNotFoundException if product not found
     */
    BeautyProductDTO updateProduct(String id, BeautyProductDTO dto);

    /**
     * Partially update an existing product (PATCH operation)
     * Only non-null fields in the DTO will be updated
     * @param id Product ID to update
     * @param dto Partial product data
     * @return Updated product DTO
     * @throws com.beautywithrumi.exception.ResourceNotFoundException if product not found
     */
    BeautyProductDTO partialUpdateProduct(String id, BeautyProductDTO dto);

    /**
     * Delete a product by ID
     * @param id Product ID to delete
     * @throws com.beautywithrumi.exception.ResourceNotFoundException if product not found
     */
    void deleteProduct(String id);
}
