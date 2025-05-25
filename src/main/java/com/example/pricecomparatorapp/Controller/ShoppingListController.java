package com.example.pricecomparatorapp.Controller;


import com.example.pricecomparatorapp.Dto.*;
import com.example.pricecomparatorapp.Model.ShoppingList;
import com.example.pricecomparatorapp.Service.ShoppingListService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shopping-lists")
@SecurityRequirement(name = "bearerAuth")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final ShoppingListMapper shoppingListMapper;
    public ShoppingListController(ShoppingListService shoppingListService,
                                  ShoppingListMapper shoppingListMapper) {
        this.shoppingListService = shoppingListService;
        this.shoppingListMapper = shoppingListMapper;
    }

    /**
     * Creates a new shopping list.
     *
     * @param request The request containing the details for the shopping list.
     * @return The created shopping list as a response DTO.
     */
    @PostMapping
    public ShoppingListResponse createShoppingList(
            @Valid @RequestBody CreateShoppingListRequest request) {
        ShoppingList createdList = shoppingListService.createShoppingList(request);
        return shoppingListMapper.toDto(createdList);
    }
    /**
     * Retrieves all shopping lists for the authenticated user.
     *
     * @return A list of shopping list response DTOs.
     */
    @GetMapping("/{id}")
    public ShoppingListResponse getShoppingList(@PathVariable Long id) {
        return shoppingListMapper.toDto(shoppingListService.getShoppingListById(id));
    }

    /**
     * Retrieves all shopping lists for the authenticated user.
     *
     * @return A list of shopping list response DTOs.
     */
    @PutMapping("/{id}")
    public ShoppingList updateShoppingList(
            @PathVariable Long id,
            @RequestBody UpdateShoppingListRequest request) {
        return shoppingListService.updateShoppingList(id, request);
    }
    /**
     * Deletes a shopping list by its ID.
     *
     * @param id The ID of the shopping list to delete.
     */
    @PostMapping("/{id}/optimize")
    public OptimizedShoppingListResponse optimizeShoppingList(@PathVariable Long id) {
        return shoppingListService.optimizeShoppingList(id);
    }
}