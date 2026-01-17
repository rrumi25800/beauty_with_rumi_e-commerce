package com.beautywithrumi.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Data Transfer Object for BeautyProduct
 * 
 * DTOs separate the API layer from the persistence layer, demonstrating:
 * - Separation of concerns (OOP principle)
 * - Encapsulation of data transfer logic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeautyProductDTO {

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
