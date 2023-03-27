package org.zaripov.iStore.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zaripov.iStore.core.entities.Category;
import org.zaripov.iStore.core.repositories.CategoryRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Optional<Category> findByTitle(String categoryTitle) {
        return categoryRepository.findByTitle(categoryTitle);
    }
}
