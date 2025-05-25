package com.example.pricecomparatorapp.Model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @CsvBindByName(column = "product_id")
    @Transient
    private String csvProductId;

    @CsvBindByName(column = "product_name")
    @Transient
    private String productName;

    @CsvBindByName(column = "brand")
    @Transient
    private String brand;

    @CsvBindByName(column = "package_quantity")
    @Transient
    private Double packageQuantity;

    @CsvBindByName(column = "package_unit")
    @Transient
    private String packageUnit;

    @CsvBindByName(column = "product_category")
    @Transient
    private String category;


    @CsvBindByName(column = "from_date")
    @CsvDate("yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fromDate;

    @CsvBindByName(column = "to_date")
    @CsvDate("yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate toDate;

    @CsvBindByName(column = "percentage_of_discount")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal percentage;


    @Column(name = "original_price", precision = 10, scale = 2)
    private BigDecimal originalPrice;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


    @PrePersist
    public void resolveRelationships() {

        if (this.product == null && this.csvProductId != null) {
            throw new IllegalStateException("Product relationship must be set before persisting");
        }
        if (this.store == null) {
            throw new IllegalStateException("Store relationship must be set before persisting");
        }
        if (this.originalPrice == null) {
            this.originalPrice = product.getPrice();
        }
    }
    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Store getStore() {
        return store;
    }

    public String getCsvProductId() {
        return csvProductId;
    }

    public String getProductName() {
        return productName;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPackageQuantity() {
        return packageQuantity;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }


}