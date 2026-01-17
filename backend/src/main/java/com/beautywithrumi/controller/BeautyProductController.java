package com.beautywithrumi.controller;

import com.beautywithrumi.dto.BeautyProductDTO;
import com.beautywithrumi.service.BeautyProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Beauty Product operations
 * 
 * Demonstrates OOP principles:
 * - Single Responsibility: Handles only HTTP request/response
 * - Delegation: Delegates business logic to service layer
 * - Dependency Injection: Service injected via constructor
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class BeautyProductController {

    private final BeautyProductService productService;

    /**
     * GET /api/products
     * Retrieve all beauty products
     */
    @GetMapping
    public ResponseEntity<List<BeautyProductDTO>> getAllProducts() {
        List<BeautyProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * GET /api/products/{id}
     * Retrieve a single product by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BeautyProductDTO> getProductById(@PathVariable String id) {
        BeautyProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * POST /api/products
     * Create a new beauty product
     */
    @PostMapping
    public ResponseEntity<BeautyProductDTO> createProduct(
            @Valid @RequestBody BeautyProductDTO productDTO) {
        BeautyProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * PUT /api/products/{id}
     * Update an entire product (full replacement)
     */
    @PutMapping("/{id}")
    public ResponseEntity<BeautyProductDTO> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody BeautyProductDTO productDTO) {
        BeautyProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * PATCH /api/products/{id}
     * Partially update a product (only provided fields)
     */
    @PatchMapping("/{id}")
    public ResponseEntity<BeautyProductDTO> partialUpdateProduct(
            @PathVariable String id,
            @RequestBody BeautyProductDTO productDTO) {
        BeautyProductDTO updatedProduct = productService.partialUpdateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * DELETE /api/products/{id}
     * Delete a product by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
