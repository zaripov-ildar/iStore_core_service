package org.zaripov.iStore.core.converters;

import org.springframework.stereotype.Component;
import org.zaripov.iStore.core.dtos.OrderItemDto;
import org.zaripov.iStore.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem item) {
        return new OrderItemDto(
                item.getProduct().getId(),
                item.getProduct().getTitle(),
                item.getQuantity(),
                item.getPricePerProduct(),
                item.getPrice()
        );
    }
}
