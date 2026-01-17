package com.beautywithrumi.service.impl;

import com.beautywithrumi.dto.BeautyProductDTO;
import com.beautywithrumi.entity.BeautyProduct;
import com.beautywithrumi.exception.ResourceNotFoundException;
import com.beautywithrumi.repository.BeautyProductRepository;
import com.beautywithrumi.service.BeautyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of BeautyProductService
 * 
 * Demonstrates OOP principles:
 * - Encapsulation: Business logic encapsulated in service layer
 * - Single Responsibility: Handles only business logic for products
 * - Dependency Injection: Uses constructor injection (immutable dependencies)
 */
@Service
@RequiredArgsConstructor
@Transactional
public class BeautyProductServiceImpl implements BeautyProductService {

    private final BeautyProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<BeautyProductDTO> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BeautyProductDTO getProductById(String id) {
        BeautyProduct product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Beauty product not found with id: " + id));
        return mapToDTO(product);
    }

    @Override
    public BeautyProductDTO createProduct(BeautyProductDTO dto) {
        BeautyProduct product = mapToEntity(dto);
        BeautyProduct savedProduct = repository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public BeautyProductDTO updateProduct(String id, BeautyProductDTO dto) {
        // Verify product exists
        BeautyProduct existingProduct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Beauty product not found with id: " + id));

        // Update all fields (full replacement)
        existingProduct.setName(dto.getName());
        existingProduct.setBrand(dto.getBrand());
        existingProduct.setCategory(dto.getCategory());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setShade(dto.getShade());
        existingProduct.setSkinType(dto.getSkinType());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setInStock(dto.getInStock());

        BeautyProduct updatedProduct = repository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    @Override
    public BeautyProductDTO partialUpdateProduct(String id, BeautyProductDTO dto) {
        // Verify product exists
        BeautyProduct existingProduct = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Beauty product not found with id: " + id));

        // Update only non-null fields (partial update)
        if (dto.getName() != null) {
            existingProduct.setName(dto.getName());
        }
        if (dto.getBrand() != null) {
            existingProduct.setBrand(dto.getBrand());
        }
        if (dto.getCategory() != null) {
            existingProduct.setCategory(dto.getCategory());
        }
        if (dto.getPrice() != null) {
            existingProduct.setPrice(dto.getPrice());
        }
        if (dto.getShade() != null) {
            existingProduct.setShade(dto.getShade());
        }
        if (dto.getSkinType() != null) {
            existingProduct.setSkinType(dto.getSkinType());
        }
        if (dto.getDescription() != null) {
            existingProduct.setDescription(dto.getDescription());
        }
        if (dto.getInStock() != null) {
            existingProduct.setInStock(dto.getInStock());
        }

        BeautyProduct updatedProduct = repository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        // Verify product exists before deleting
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Beauty product not found with id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Private helper method to map Entity to DTO
     * Encapsulates mapping logic
     */
    private BeautyProductDTO mapToDTO(BeautyProduct entity) {
        return BeautyProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .brand(entity.getBrand())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .shade(entity.getShade())
                .skinType(entity.getSkinType())
                .description(entity.getDescription())
                .inStock(entity.getInStock())
                .build();
    }

    /**
     * Private helper method to map DTO to Entity
     * Encapsulates mapping logic
     */
    private BeautyProduct mapToEntity(BeautyProductDTO dto) {
        return BeautyProduct.builder()
                .id(dto.getId())
                .name(dto.getName())
                .brand(dto.getBrand())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .shade(dto.getShade())
                .skinType(dto.getSkinType())
                .description(dto.getDescription())
                .inStock(dto.getInStock())
                .build();
    }
}
