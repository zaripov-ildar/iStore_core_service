package org.zaripov.iStore.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.zaripov.iStore.core.converters.ProductConverter;
import org.zaripov.iStore.core.dtos.ProductDto;
import org.zaripov.iStore.core.entities.Product;
import org.zaripov.iStore.core.entities.specifications.ProductSpecifications;
import org.zaripov.iStore.core.exceptions.ResourceNotFoundException;
import org.zaripov.iStore.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Page<Product> find(
            int page,
            int pageSize,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            String titlePart
    ) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGraterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));

    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Can't find product #" + id));
    }

    public void createNewProduct(ProductDto productDto) {
        productRepository.save(productConverter.toProduct(productDto));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
