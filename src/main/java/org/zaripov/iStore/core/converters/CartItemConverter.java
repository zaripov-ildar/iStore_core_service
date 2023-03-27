package org.zaripov.iStore.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zaripov.iStore.core.dtos.CartItemDto;
import org.zaripov.iStore.core.entities.Order;
import org.zaripov.iStore.core.entities.OrderItem;
import org.zaripov.iStore.core.services.ProductService;

@RequiredArgsConstructor
@Component
public class CartItemConverter {
    private final ProductService productService;

    public OrderItem toOrderItem(CartItemDto cartItemDto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPricePerProduct(cartItemDto.getPricePerProduct());
        orderItem.setQuantity(cartItemDto.getQuantity());
        orderItem.setPrice(cartItemDto.getTotalPrice());
        orderItem.setProduct(
                productService.findById(cartItemDto.getProductId())
        );
        return orderItem;
    }
}
