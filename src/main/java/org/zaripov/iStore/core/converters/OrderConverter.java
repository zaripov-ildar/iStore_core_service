package org.zaripov.iStore.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zaripov.iStore.core.dtos.OrderDto;
import org.zaripov.iStore.core.entities.Order;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderDto toOrderDto(Order order) {


        return new OrderDto(
                order.getId(),
                order.getItemList()
                        .stream()
                        .map(orderItemConverter::entityToDto)
                        .collect(Collectors.toList()), order.getTotalPrice());
    }
}
