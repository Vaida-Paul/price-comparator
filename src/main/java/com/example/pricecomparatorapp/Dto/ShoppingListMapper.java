package com.example.pricecomparatorapp.Dto;

import com.example.pricecomparatorapp.Model.ShoppingList;
import com.example.pricecomparatorapp.Model.ShoppingListItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShoppingListMapper {
    public ShoppingListResponse toDto(ShoppingList shoppingList) {
        ShoppingListResponse dto = new ShoppingListResponse();
        dto.setId(shoppingList.getId());
        dto.setName(shoppingList.getName());
        dto.setCreatedAt(shoppingList.getCreatedAt());
        dto.setUpdatedAt(shoppingList.getUpdatedAt());


        UserDto userDto = new UserDto();
        userDto.setId(shoppingList.getUser().getId());
        userDto.setUsername(shoppingList.getUser().getUsername());
        dto.setUser(userDto);

        if (shoppingList.getItems() != null) {
            dto.setItems(shoppingList.getItems().stream()
                    .map(this::mapItemToDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private ShoppingListItemDto mapItemToDto(ShoppingListItem item) {
        ShoppingListItemDto dto = new ShoppingListItemDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());

        ProductDto productDto = new ProductDto();
        productDto.setId(item.getProduct().getId());
        productDto.setName(item.getProduct().getName());

        dto.setProduct(productDto);

        return dto;
    }
}