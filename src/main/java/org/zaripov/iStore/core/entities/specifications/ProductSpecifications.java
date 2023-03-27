package org.zaripov.iStore.core.entities.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.zaripov.iStore.core.entities.Product;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> priceGraterOrEqualsThan(BigDecimal minPrice) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
    }

    public static Specification<Product> priceLessOrEqualsThan(BigDecimal maxPrice) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
