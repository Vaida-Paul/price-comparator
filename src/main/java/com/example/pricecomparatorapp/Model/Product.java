package com.example.pricecomparatorapp.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(name = "products")
public class Product {
    @Id
    @CsvBindByName(column = "product_id")
    @Column(length = 10)
    private String id;

    @CsvBindByName(column = "product_name")
    @Column(nullable = false, length = 100)
    private String name;

    @CsvBindByName(column = "product_category")
    @Column(nullable = false, length = 50)
    private String category;

    @CsvBindByName(column = "brand")
    @Column(length = 50)
    private String brand;

    @CsvBindByName(column = "package_quantity")
    @Column(name = "package_quantity")
    private Double packageQuantity;

    @CsvBindByName(column = "package_unit")
    @Column(name = "package_unit", length = 10)
    private String packageUnit;

//    @CsvBindByName(column = "product_standard_quantity")
//    @Column(name = "standard_unit", length = 10)
//    private String standardUnit;

    @CsvBindByName(column = "price")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;


    @CsvBindByName(column = "currency")
    @Column(length = 3)
    private String currency;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isEmpty()) {
            throw new IllegalStateException("Product ID must be set before persisting");
        }
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getBrand() { return brand; }
    public Double getPackageQuantity() { return packageQuantity; }
    public String getPackageUnit() { return packageUnit; }
    public BigDecimal getPrice() { return price; }
    public String getCurrency() { return currency; }
}