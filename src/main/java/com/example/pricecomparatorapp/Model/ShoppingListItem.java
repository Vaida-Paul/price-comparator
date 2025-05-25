package com.example.pricecomparatorapp.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shopping_list_items")
public class ShoppingListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity = 1;

    @ManyToOne
    @JoinColumn(name = "preferred_store_id")
    private Store preferredStore;

    @Column(name = "added_at", updatable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ShoppingList getShoppingList() {
        return shoppingList;
    }
    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Store getPreferredStore() {
        return preferredStore;
    }
    public void setPreferredStore(Store preferredStore) {
        this.preferredStore = preferredStore;
    }
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

}
