package com.example.pricecomparatorapp.Service;


import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
}