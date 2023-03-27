package org.zaripov.iStore.core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto {
    private List<ProductDto> products;
    private int page;
    private int pageSize;
    private int totalPages;


}
