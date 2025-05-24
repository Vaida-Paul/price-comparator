package com.example.pricecomparatorapp.Service;

import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Repository.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(String category, String brand) {
        Specification<Product> spec = Specification.where(null);
        if (category != null && !category.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category"), category));
        }
        if (brand != null && !brand.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brand));
        }
        return productRepository.findAll(spec);
    }
}
