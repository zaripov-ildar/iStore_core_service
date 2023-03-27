package org.zaripov.iStore.core.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem extends BaseEntity {
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "pricePerProduct")
    private BigDecimal pricePerProduct;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
