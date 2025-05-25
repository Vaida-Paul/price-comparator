package com.example.pricecomparatorapp.Controller;


import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Service.DiscountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/api/discounts")
@SecurityRequirement(name = "bearerAuth")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Retrieves all discounts.
     *
     * @return List of all discounts.
     */
    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    /**
     * Retrieves current discounts.
     *
     * @return List of current discounts.
     */
    @GetMapping("/current")
    public List<Discount> getCurrentDiscounts() {
        return discountService.getCurrentDiscounts();
    }

    /**
     * Retrieves the best discounts, limited by the specified number.
     *
     * @param limit The maximum number of discounts to retrieve.
     * @return List of the best discounts.
     */
    @GetMapping("/best")
    public List<Discount> getBestDiscounts(
            @RequestParam(defaultValue = "10") int limit) {
        return discountService.getBestDiscounts(limit);
    }

    /**
     * Retrieves new discounts.
     *
     * @return List of new discounts.
     */
    @GetMapping("/new")
    public List<Discount> getNewDiscounts() {
        return discountService.getNewDiscounts();
    }

    /**
     * Retrieves discounts by store name.
     *
     * @param storeName The name of the store to filter discounts.
     * @return List of discounts for the specified store.
     */
    @GetMapping("/store/{storeName}")
    public List<Discount> getDiscountsByStore(
            @PathVariable String storeName) {
        return discountService.getDiscountsByStore(storeName);
    }
    /**
     * Retrieves discounts by category.
     *
     * @param category The category to filter discounts.
     * @return List of discounts for the specified category.
     */
    @GetMapping("/category/{category}")
    public List<Discount> getDiscountsByCategory(
            @PathVariable String category) {
        return discountService.getDiscountsByCategory(category);
    }
}