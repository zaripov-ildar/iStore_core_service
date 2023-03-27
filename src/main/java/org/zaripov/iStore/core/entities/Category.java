package org.zaripov.iStore.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category extends BaseEntity{
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
