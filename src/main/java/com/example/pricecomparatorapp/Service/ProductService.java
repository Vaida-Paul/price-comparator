package com.example.pricecomparatorapp.Service;

import com.example.pricecomparatorapp.Dto.PriceHistoryDto;
import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Repository.DiscountRepository;
import com.example.pricecomparatorapp.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }


    public List<Product> getAllProducts(String category, String brand) {
        Specification<Product> spec = Specification.where(null);
        if (category != null && !category.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category"), category));
        }
        if (brand != null && !brand.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("brand"), brand));
        }
        return productRepository.findAll(spec);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<PriceHistoryDto> getPriceHistory(String productId) {
        return discountRepository.findPriceHistoryByProductId(productId);
    }
}