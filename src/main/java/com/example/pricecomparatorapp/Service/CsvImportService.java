package com.example.pricecomparatorapp.Service;

import com.example.pricecomparatorapp.Model.Discount;
import com.example.pricecomparatorapp.Model.Product;
import com.example.pricecomparatorapp.Model.Store;
import com.example.pricecomparatorapp.Repository.DiscountRepository;
import com.example.pricecomparatorapp.Repository.ProductRepository;
import com.example.pricecomparatorapp.Repository.StoreRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@Service
public class CsvImportService {
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;
    private final StoreRepository storeRepository;
    @Autowired
    public CsvImportService(ProductRepository productRepository,
                            DiscountRepository discountRepository,
                            StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
        this.storeRepository = storeRepository;
    }

    public void importProductsFromCsv(String filePath) throws IOException {
        List<Product> products = new CsvToBeanBuilder<Product>(new FileReader(filePath))
                .withType(Product.class)
                .withSeparator(';')
                .build()
                .parse();
        productRepository.saveAll(products);
    }

    public void importDiscountsFromCsv(String filePath, String storeName) throws IOException {
        List<Discount> discounts = new CsvToBeanBuilder<Discount>(new FileReader(filePath))
                .withType(Discount.class)
                .withSeparator(';')
                .build()
                .parse();

        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new RuntimeException("Store not found: " + storeName));

        discounts.forEach(discount -> {
            Product product = productRepository.findById(discount.getCsvProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found: " + discount.getCsvProductId()));
            discount.setProduct(product);
            discount.setStore(store);
            discount.setOriginalPrice(product.getPrice());
            discountRepository.save(discount);
        });
    }
}
