package org.zaripov.iStore.core.dtos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal totalPrice;
    private int quantity;
}
