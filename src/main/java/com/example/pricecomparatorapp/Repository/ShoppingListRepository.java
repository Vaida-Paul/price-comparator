package com.example.pricecomparatorapp.Repository;

import com.example.pricecomparatorapp.Model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

    @Query("SELECT sl FROM ShoppingList sl LEFT JOIN FETCH sl.items WHERE sl.id = :id")
    Optional<ShoppingList> findByIdWithItems(@Param("id") Long id);
}