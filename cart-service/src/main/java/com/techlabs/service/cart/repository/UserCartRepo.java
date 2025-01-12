package com.techlabs.service.cart.repository;

import com.techlabs.service.cart.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserCartRepo extends JpaRepository<UserCart, Long> {
    Optional<UserCart> findByUserId(Long userId);
}
