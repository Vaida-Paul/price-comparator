package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
    List<ShoppingListItem> findByShoppingListId(Long shoppingListId);
    void deleteByShoppingListId(Long shoppingListId);
}