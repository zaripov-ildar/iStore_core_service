package org.zaripov.iStore.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zaripov.iStore.core.converters.CartItemConverter;
import org.zaripov.iStore.core.dtos.CartDto;
import org.zaripov.iStore.core.entities.Order;
import org.zaripov.iStore.core.integrations.CartServiceIntegration;
import org.zaripov.iStore.core.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartItemConverter cartItemConverter;
    private final CartServiceIntegration cartServiceIntegration;


    public void createNewOrder(String username, String cartId) {
        CartDto cartDto = cartServiceIntegration.getUserCart(cartId);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItemList(
                cartDto.getCartItems().stream()
                        .map(cartItemDto -> cartItemConverter.toOrderItem(cartItemDto, order))
                        .collect(Collectors.toList())
        );
        orderRepository.save(order);
        cartServiceIntegration.clear(cartId);
    }

    public List<Order> findByUserName(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
