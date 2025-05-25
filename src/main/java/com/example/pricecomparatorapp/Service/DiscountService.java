package com.example.pricecomparatorapp.Service;


import com.example.pricecomparatorapp.Dto.OptimizedItem;
import com.example.pricecomparatorapp.Dto.OptimizedShoppingListResponse;
import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Model.ShoppingList;
import com.example.pricecomparatorapp.Model.ShoppingListItem;
import com.example.pricecomparatorapp.Repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public List<Discount> getCurrentDiscounts() {
        return discountRepository.findCurrentDiscounts();
    }

    public List<Discount> getBestDiscounts(int limit) {
        return discountRepository.findBestDiscounts()
                .stream()
                .limit(limit)
                .toList();
    }

    public List<Discount> getNewDiscounts() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);
        return discountRepository.findNewDiscounts(cutoffTime);
    }

    public List<Discount> getDiscountsByStore(String storeName) {
        return discountRepository.findByStore(storeName);
    }

    public List<Discount> getDiscountsByCategory(String category) {
        return discountRepository.findByCategory(category);
    }

    public BigDecimal calculateDiscountedPrice(Discount discount) {
        return discount.getOriginalPrice()
                .multiply(BigDecimal.ONE.subtract(
                        discount.getPercentage().divide(BigDecimal.valueOf(100))));
    }

    public OptimizedShoppingListResponse optimizeShoppingList(ShoppingList shoppingList) {
        List<OptimizedItem> optimizedItems = new ArrayList<>();
        BigDecimal totalOriginal = BigDecimal.ZERO;
        BigDecimal totalDiscounted = BigDecimal.ZERO;

        for (ShoppingListItem item : shoppingList.getItems()) {
            Product product = item.getProduct();
            List<Discount> discounts = discountRepository.findByProductId(product.getId());

            Discount bestDiscount = findBestDiscount(discounts);

            OptimizedItem optimizedItem = new OptimizedItem();
            optimizedItem.setProductId(product.getId());
            optimizedItem.setProductName(product.getName());
            optimizedItem.setQuantity(item.getQuantity());

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalOriginal = totalOriginal.add(itemTotal);

            if (bestDiscount != null) {
                BigDecimal discountMultiplier = BigDecimal.ONE.subtract(
                        bestDiscount.getPercentage().divide(BigDecimal.valueOf(100)));
                BigDecimal discountedPrice = itemTotal.multiply(discountMultiplier);

                optimizedItem.setStoreName(bestDiscount.getStore().getName());
                optimizedItem.setOriginalPrice(itemTotal);
                optimizedItem.setDiscountedPrice(discountedPrice);
                optimizedItem.setDiscountPercentage(bestDiscount.getPercentage());

                totalDiscounted = totalDiscounted.add(discountedPrice);
            } else {
                optimizedItem.setStoreName("No discount available");
                optimizedItem.setOriginalPrice(itemTotal);
                optimizedItem.setDiscountedPrice(itemTotal);
                optimizedItem.setDiscountPercentage(BigDecimal.ZERO);

                totalDiscounted = totalDiscounted.add(itemTotal);
            }

            optimizedItems.add(optimizedItem);
        }

        OptimizedShoppingListResponse response = new OptimizedShoppingListResponse();
        response.setShoppingListId(shoppingList.getId());
        response.setName(shoppingList.getName());
        response.setTotalOriginalPrice(totalOriginal);
        response.setTotalDiscountedPrice(totalDiscounted);
        response.setTotalSavings(totalOriginal.subtract(totalDiscounted));
        response.setOptimizedItems(optimizedItems);

        return response;
    }

    private Discount findBestDiscount(List<Discount> discounts) {
        return discounts.stream()
                .filter(d -> d.getToDate().isAfter(LocalDate.now()))
                .max(Comparator.comparing(Discount::getPercentage))
                .orElse(null);
    }

}