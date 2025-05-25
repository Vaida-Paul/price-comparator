package com.example.pricecomparatorapp.Service;


import com.example.pricecomparatorapp.Dto.*;
import com.example.pricecomparatorapp.Model.*;
import com.example.pricecomparatorapp.Repository.*;
import com.example.pricecomparatorapp.Service.DiscountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final DiscountService discountService;

    public ShoppingListService(ShoppingListRepository shoppingListRepository,
                                ShoppingListItemRepository shoppingListItemRepository,
                                ProductRepository productRepository,
                                UserRepository userRepository,
                                DiscountService discountService) {
        this.shoppingListRepository = shoppingListRepository;
        this.shoppingListItemRepository = shoppingListItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.discountService = discountService;
    }


    public ShoppingList createShoppingList(CreateShoppingListRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(request.getName());
        shoppingList.setUser(user);

        ShoppingList savedList = shoppingListRepository.save(shoppingList);

        if (request.getItems() != null && !request.getItems().isEmpty()) {
            addItemsToList(savedList.getId(), request.getItems());
        }


        return shoppingListRepository.findByIdWithItems(savedList.getId())
                .orElse(savedList);
    }

    public ShoppingList getShoppingListById(Long id) {
        return shoppingListRepository.findByIdWithItems(id)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));
    }

    private void addItemsToList(Long listId, List<ShoppingListItemRequest> items) {
        ShoppingList shoppingList = shoppingListRepository.getReferenceById(listId);

        List<ShoppingListItem> listItems = items.stream()
                .map(item -> {
                    Product product = productRepository.findById(item.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Product not found: " + item.getProductId()));

                    ShoppingListItem listItem = new ShoppingListItem();
                    listItem.setShoppingList(shoppingList);
                    listItem.setProduct(product);
                    listItem.setQuantity(item.getQuantity());
                    return listItem;
                })
                .collect(Collectors.toList());

        shoppingListItemRepository.saveAll(listItems);
    }

//    public ShoppingList getShoppingListById(Long id) {
//        return shoppingListRepository.findByIdWithItems(id)
//                .orElseThrow(() -> new RuntimeException("Shopping list not found"));
//    }

    @Transactional
    public ShoppingList updateShoppingList(Long id, UpdateShoppingListRequest request) {
        ShoppingList existingList = getShoppingListById(id);

        if (request.getName() != null) {
            existingList.setName(request.getName());
        }


        if (request.getItems() != null) {
            shoppingListItemRepository.deleteByShoppingListId(id);
            addItemsToList(id, request.getItems());
        }

        return shoppingListRepository.save(existingList);
    }

//    @Transactional
//    public void addItemsToList(Long listId, List<ShoppingListItemRequest> items) {
//        ShoppingList shoppingList = shoppingListRepository.findById(listId)
//                .orElseThrow(() -> new RuntimeException("Shopping list not found"));
//
//        items.forEach(item -> {
//            Product product = productRepository.findById(item.getProductId())
//                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));
//
//            ShoppingListItem listItem = new ShoppingListItem();
//            listItem.setShoppingList(shoppingList);
//            listItem.setProduct(product);
//            listItem.setQuantity(item.getQuantity());
//
//            shoppingListItemRepository.save(listItem);
//        });
//    }

    @Transactional
    public OptimizedShoppingListResponse optimizeShoppingList(Long listId) {
        ShoppingList shoppingList = getShoppingListById(listId);
        return discountService.optimizeShoppingList(shoppingList);
    }
}
