package com.micropos.cart.mapper;

import com.micropos.cart.model.Item;
import com.micropos.dto.CartItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartItemDto> toItemsDto(List<Item> items);

    List<Item> toCart(List<CartItemDto> cartItems);

    CartItemDto toItemDto(Item cartItem);

    Item toItem(CartItemDto cartItem);
}
