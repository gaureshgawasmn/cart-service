package com.techlabs.service.cart.mapper;

import com.techlabs.service.cart.entity.CartItem;
import com.techlabs.service.cart.entity.UserCart;
import com.techlabs.service.cart.json.CartItemDTO;
import com.techlabs.service.cart.json.UserCartDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper MAPPER = Mappers.getMapper(CartMapper.class);

    UserCartDTO userCartToCartDTO(UserCart userCart);

    List<CartItemDTO> cartItemsToCartItemDTOs(List<CartItem> cartItems);

    UserCart userCartDTOToUserCart(UserCartDTO userCartDTO);

    List<CartItem> cartItemsDTOToCartItems(List<CartItemDTO> items);
}
