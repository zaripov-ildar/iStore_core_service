package org.zaripov.iStore.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zaripov.iStore.core.converters.OrderConverter;
import org.zaripov.iStore.core.dtos.OrderDto;
import org.zaripov.iStore.core.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping("/{cartId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username, @PathVariable String cartId){
        orderService.createNewOrder(username, cartId);

    }

    @GetMapping()
    public List<OrderDto> getOrders(@RequestHeader String username){
        return orderService.findByUserName(username)
                .stream()
                .map(orderConverter::toOrderDto)
                .toList();
    }
}
