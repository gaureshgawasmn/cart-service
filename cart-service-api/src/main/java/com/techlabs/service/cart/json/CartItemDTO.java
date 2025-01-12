package com.techlabs.service.cart.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;

    @JsonProperty
    public Double itemTotal() {
        return price * quantity;
    }
}
