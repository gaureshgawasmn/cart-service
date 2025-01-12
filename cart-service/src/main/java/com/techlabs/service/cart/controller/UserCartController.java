package com.techlabs.service.cart.controller;

import com.techlabs.service.cart.entity.UserCart;
import com.techlabs.service.cart.json.UserCartDTO;
import com.techlabs.service.cart.mapper.CartMapper;
import com.techlabs.service.cart.repository.UserCartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequestMapping("/user-cart")
public class UserCartController {

    private static final CartMapper cartMapper = CartMapper.MAPPER;
    private final UserCartRepo userCartRepo;

    public UserCartController(UserCartRepo userCartRepo) {
        this.userCartRepo = userCartRepo;
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<UserCartDTO> createUserCart(@RequestBody UserCartDTO userCartDTO) {
        UserCart userCart = cartMapper.userCartDTOToUserCart(userCartDTO);
        if (nonNull(userCart.getItems())) {
            UserCart finalUserCart = userCart;
            userCart.getItems().forEach(cartItem -> cartItem.setUserCart(finalUserCart));
        }

        userCart = userCartRepo.save(userCart);
        userCartDTO.setId(userCart.getId());

        log.info("Created cart with id: {}", userCart.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userCartDTO);
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<UserCartDTO> getUserCart(@PathVariable Long userId) {
        UserCart userCart = userCartRepo.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));

        UserCartDTO userCartDTO = cartMapper.userCartToCartDTO(userCart);
        log.info("Retrieved cart with id: {}", userCart.getId());
        return ResponseEntity.ok(userCartDTO);
    }
}
