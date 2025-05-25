package com.example.pricecomparatorapp.Service;


import com.example.pricecomparatorapp.Dto.CreatePriceAlertRequest;
import com.example.pricecomparatorapp.Dto.PriceAlertDto;
import com.example.pricecomparatorapp.Model.PriceAlert;
import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Model.User;
import com.example.pricecomparatorapp.Repository.PriceAlertRepository;
import com.example.pricecomparatorapp.Repository.ProductRepository;
import com.example.pricecomparatorapp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class PriceAlertService {
    private final PriceAlertRepository priceAlertRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PriceAlertService (PriceAlertRepository priceAlertRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public PriceAlertDto createPriceAlert(CreatePriceAlertRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        if (priceAlertRepository.existsByUserIdAndProductId(user.getId(), product.getId())) {
            throw new RuntimeException("Price alert already exists for this product");
        }

        PriceAlert alert = new PriceAlert();
        alert.setUser(user);
        alert.setProduct(product);
        alert.setTargetPrice(request.getTargetPrice());
        alert.setIsActive(true);

        PriceAlert savedAlert = priceAlertRepository.save(alert);
        return mapToDto(savedAlert);
    }

    public List<PriceAlertDto> getUserAlerts(Long userId) {
        return priceAlertRepository.findByUserIdAndIsActiveTrue(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private PriceAlertDto mapToDto(PriceAlert alert) {
        PriceAlertDto dto = new PriceAlertDto();
        dto.setId(alert.getId());
        dto.setProductId(alert.getProduct().getId());
        dto.setProductName(alert.getProduct().getName());
        dto.setCurrentPrice(alert.getProduct().getPrice());
        dto.setTargetPrice(alert.getTargetPrice());
        dto.setIsActive(alert.getIsActive());
        dto.setCreatedAt(alert.getCreatedAt());
        dto.setTriggeredAt(alert.getTriggeredAt());
        return dto;
    }
}
