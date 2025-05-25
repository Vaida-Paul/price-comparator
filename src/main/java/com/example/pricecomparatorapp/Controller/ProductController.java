package com.example.pricecomparatorapp.Controller;

import com.example.pricecomparatorapp.Dto.PriceHistoryDto;
import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@SecurityRequirement(name = "bearerAuth")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves all products, optionally filtered by category, brand, or store.
     *
     * @param category  Optional category to filter products.
     * @param brand     Optional brand to filter products.
     * @param storeName Optional store name to filter products.
     * @return List of products matching the filters.
     */
    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "store", required = false) String storeName) {
        return productService.getAllProducts(category, brand);
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    /**
     * Searches for products by name.
     *
     * @param name The name of the product to search for.
     * @return List of products matching the search criteria.
     */
    @GetMapping("/search")
    public List<Product> searchProductsByName(
            @RequestParam(value = "name") String name) {
        return productService.searchProductsByName(name);
    }
    /**
     * Retrieves the price history of a product by its ID.
     *
     * @param id The ID of the product whose price history is to be retrieved.
     * @return List of price history records for the specified product.
     */
    @GetMapping("/{id}/price-history")
    public List<PriceHistoryDto> getPriceHistory(@PathVariable String id) {
        return productService.getPriceHistory(id);
    }
}
