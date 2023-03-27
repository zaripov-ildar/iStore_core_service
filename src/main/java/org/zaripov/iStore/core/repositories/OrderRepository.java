package org.zaripov.iStore.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zaripov.iStore.core.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUsername(String username);
}
