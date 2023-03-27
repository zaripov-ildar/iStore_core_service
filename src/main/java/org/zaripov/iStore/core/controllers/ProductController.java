package org.zaripov.iStore.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zaripov.iStore.core.converters.PageConverter;
import org.zaripov.iStore.core.converters.ProductConverter;
import org.zaripov.iStore.core.dtos.PageDto;
import org.zaripov.iStore.core.dtos.ProductDto;
import org.zaripov.iStore.core.services.ProductService;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;

    @GetMapping
    public PageDto getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "1") Integer pageSize,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name = "titlePart", required = false) String titlePart
    ) {
        page = Math.max(1, page);
        pageSize = Math.max(1, pageSize);
        return pageConverter.toPageDto(
                productService.find(page, pageSize, minPrice, maxPrice, titlePart)
                        .map(productConverter::toDto)
        );


    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productConverter.toDto(productService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
