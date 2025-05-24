package com.example.pricecomparatorapp.Controller;


import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Service.DiscountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public List<Discount> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/current")
    public List<Discount> getCurrentDiscounts() {
        return discountService.getCurrentDiscounts();
    }

    @GetMapping("/best")
    public List<Discount> getBestDiscounts(
            @RequestParam(defaultValue = "10") int limit) {
        return discountService.getBestDiscounts(limit);
    }

    @GetMapping("/new")
    public List<Discount> getNewDiscounts() {
        return discountService.getNewDiscounts();
    }

    @GetMapping("/store/{storeName}")
    public List<Discount> getDiscountsByStore(
            @PathVariable String storeName) {
        return discountService.getDiscountsByStore(storeName);
    }

    @GetMapping("/category/{category}")
    public List<Discount> getDiscountsByCategory(
            @PathVariable String category) {
        return discountService.getDiscountsByCategory(category);
    }
}