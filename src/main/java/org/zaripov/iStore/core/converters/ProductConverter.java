package org.zaripov.iStore.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zaripov.iStore.core.dtos.ProductDto;
import org.zaripov.iStore.core.entities.Product;
import org.zaripov.iStore.core.exceptions.ResourceNotFoundException;
import org.zaripov.iStore.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public ProductDto toDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getCategory().getTitle());
    }

    public Product toProduct(ProductDto productDto) {
        return new Product(
                productDto.getTitle(),
                productDto.getPrice(),
                categoryService.findByTitle(productDto.getCategoryTitle())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Can't find category #" + productDto.getCategoryTitle())
                        )
        );
    }
}
