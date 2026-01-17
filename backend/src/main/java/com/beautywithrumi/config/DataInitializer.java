package com.beautywithrumi.config;

import com.beautywithrumi.entity.BeautyProduct;
import com.beautywithrumi.repository.BeautyProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Data Initializer for MongoDB
 * Loads sample beauty products on application startup if database is empty
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BeautyProductRepository repository;

    @Override
    public void run(String... args) {
        // Only initialize data if the database is empty
        if (repository.count() == 0) {
            List<BeautyProduct> products = List.of(
                BeautyProduct.builder()
                    .name("Rose Glow Serum")
                    .brand("Lumineux")
                    .category("Skincare")
                    .price(45.99)
                    .shade(null)
                    .skinType("All")
                    .description("Hydrating rose-infused serum that gives your skin a luminous glow. Perfect for achieving that dewy, radiant look!")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Pink Velvet Lipstick")
                    .brand("Bella Beauty")
                    .category("Makeup")
                    .price(22.50)
                    .shade("Dusty Rose")
                    .skinType("All")
                    .description("Creamy, long-lasting lipstick with a velvety matte finish. This beautiful dusty rose shade complements all skin tones.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Lavender Dream Hair Mask")
                    .brand("Silk & Shine")
                    .category("Haircare")
                    .price(28.00)
                    .shade(null)
                    .skinType("All")
                    .description("Nourishing hair mask infused with lavender oil and silk proteins. Leaves your hair soft, shiny, and beautifully scented.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Peach Blush Palette")
                    .brand("Glow Goddess")
                    .category("Makeup")
                    .price(35.00)
                    .shade("Peachy Pink")
                    .skinType("All")
                    .description("Four gorgeous peachy-pink blush shades for the perfect natural flush. Buildable, blendable, and oh-so-pretty!")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Cherry Blossom Moisturizer")
                    .brand("Petal Perfect")
                    .category("Skincare")
                    .price(38.99)
                    .shade(null)
                    .skinType("Dry")
                    .description("Ultra-hydrating moisturizer with cherry blossom extract. Soothes dry skin and leaves it feeling silky smooth.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Sparkle Highlighter")
                    .brand("Shimmer Sisters")
                    .category("Makeup")
                    .price(29.99)
                    .shade("Champagne Glow")
                    .skinType("All")
                    .description("Illuminating highlighter that gives you the most beautiful glow. Perfect for achieving that glass-skin effect!")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Vanilla Bean Body Butter")
                    .brand("Sweet Treats")
                    .category("Skincare")
                    .price(24.50)
                    .shade(null)
                    .skinType("All")
                    .description("Rich, whipped body butter with vanilla bean and shea butter. Smells divine and keeps your skin incredibly soft.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Berry Lip Gloss Set")
                    .brand("Glossy Girl")
                    .category("Makeup")
                    .price(32.00)
                    .shade("Berry Mix")
                    .skinType("All")
                    .description("Set of three berry-tinted lip glosses: raspberry, strawberry, and plum. Non-sticky formula with high shine!")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Gold Shimmer Hair Oil")
                    .brand("Luxe Locks")
                    .category("Haircare")
                    .price(26.75)
                    .shade("Golden")
                    .skinType("All")
                    .description("Lightweight hair oil with golden shimmer particles. Tames frizz and adds a gorgeous shine to your locks.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Rosewater Toner")
                    .brand("Dewy Darling")
                    .category("Skincare")
                    .price(19.99)
                    .shade(null)
                    .skinType("Oily")
                    .description("Refreshing rosewater toner that balances and hydrates oily skin. Preps your skin perfectly for makeup application.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Coral Sunset Eyeshadow Palette")
                    .brand("Sunset Dreams")
                    .category("Makeup")
                    .price(42.00)
                    .shade("Coral Tones")
                    .skinType("All")
                    .description("12 stunning coral and sunset-inspired eyeshadow shades. Mix of mattes, shimmers, and metallics for endless looks.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Coconut Milk Cleanser")
                    .brand("Pure Paradise")
                    .category("Skincare")
                    .price(21.99)
                    .shade(null)
                    .skinType("Combination")
                    .description("Gentle cleansing milk with coconut extract. Removes makeup and impurities while nourishing combination skin.")
                    .inStock(false)
                    .build(),

                BeautyProduct.builder()
                    .name("Butterfly Kiss Mascara")
                    .brand("Winged Beauty")
                    .category("Makeup")
                    .price(18.50)
                    .shade("Jet Black")
                    .skinType("All")
                    .description("Volumizing and lengthening mascara that gives you fluttery, butterfly-like lashes. Smudge-proof and long-lasting!")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Honey Glow Face Mask")
                    .brand("Golden Hour")
                    .category("Skincare")
                    .price(33.00)
                    .shade(null)
                    .skinType("Dry")
                    .description("Luxurious honey-infused face mask that deeply nourishes and gives dry skin a radiant glow. Use weekly for best results.")
                    .inStock(true)
                    .build(),

                BeautyProduct.builder()
                    .name("Lilac Curl Defining Cream")
                    .brand("Curly Cutie")
                    .category("Haircare")
                    .price(27.50)
                    .shade(null)
                    .skinType("All")
                    .description("Defines and enhances natural curls without crunch. Infused with lilac extract for a delightful scent and bounce.")
                    .inStock(true)
                    .build()
            );

            repository.saveAll(products);
            System.out.println("✨ Initialized MongoDB with " + products.size() + " beautiful products! ✨");
        } else {
            System.out.println("✨ MongoDB already contains data. Skipping initialization. ✨");
        }
    }
}
