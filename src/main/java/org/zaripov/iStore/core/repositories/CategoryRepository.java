package org.zaripov.iStore.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zaripov.iStore.core.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByTitle(String categoryTitle);
}
