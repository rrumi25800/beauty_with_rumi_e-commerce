package com.beautywithrumi.entity;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity class representing a Beauty Product in MongoDB.
 * 
 * Demonstrates OOP principles:
 * - Encapsulation: private fields with getters/setters
 * - Data hiding: fields are accessed through methods
 */
@Document(collection = "beauty_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeautyProduct {

    @Id
    private String id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Brand name is required")
    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    private String brand;

    @NotBlank(message = "Category is required")
    @Size(max = 50)
    private String category;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @Size(max = 50, message = "Shade must not exceed 50 characters")
    private String shade;

    @NotBlank(message = "Skin type is required")
    @Size(max = 30)
    private String skinType;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotNull(message = "Stock status is required")
    private Boolean inStock;
}
