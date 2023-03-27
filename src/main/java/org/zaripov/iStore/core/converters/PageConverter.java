package org.zaripov.iStore.core.converters;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.zaripov.iStore.core.dtos.PageDto;
import org.zaripov.iStore.core.dtos.ProductDto;

@Component
public class PageConverter {

    public PageDto toPageDto(Page<ProductDto> page) {
        return new PageDto(
                page.toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages()
        );
    }
}
