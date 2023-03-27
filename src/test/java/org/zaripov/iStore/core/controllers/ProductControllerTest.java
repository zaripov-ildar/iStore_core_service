package org.zaripov.iStore.core.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.zaripov.iStore.core.entities.Category;
import org.zaripov.iStore.core.entities.Product;
import org.zaripov.iStore.core.repositories.CategoryRepository;
import org.zaripov.iStore.core.repositories.ProductRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private ProductRepository productRepository;
    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        Category food = new Category();
        food.setTitle("food");
        product1 = new Product("bread", BigDecimal.ONE, food);
        product2 = new Product("apple", BigDecimal.TEN, food);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        food.setProducts(products);
        product1.setCategory(food);
        product2.setCategory(food);

        given(productRepository.findAll()).willReturn(products);
        given(productRepository.findById(1L)).willReturn(Optional.of(product1));
        given(productRepository.findById(2L)).willReturn(Optional.of(product2));
        given(productRepository.existsById(1L)).willReturn(true);
        given(categoryRepository.findByTitle("food")).willReturn(Optional.of(food));
    }

    @Test
    void getAllProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is(product1.getTitle())))
                .andExpect(jsonPath("$[1].title", is(product2.getTitle())));
    }

    @Test
    void getProductById() throws Exception {
        mockMvc.perform(get("/api/v1/products/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(product2.getTitle())))
                .andExpect(jsonPath("$.price", is(product2.getPrice().intValue())))
                .andExpect(jsonPath("$.categoryTitle", is(product2.getCategory().getTitle())));
    }

    @Test
    void get404OnGetProductWithWrongId() throws Exception {
        mockMvc.perform(get("/api/v1/products/100500")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createNewProduct() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                String.format(
                                        """
                                                {
                                                "title":"%s",
                                                "price": %s,
                                                "categoryTitle":"%s"
                                                }
                                                """
                                        , product1.getTitle(), product1.getPrice(), product1.getCategory().getTitle()
                                )
                        ))
                .andExpect(status().isCreated());//FIXME:maybe it needs to check empty fields or wrong values
    }

    @Test
    void deleteProductById() throws Exception {
        mockMvc.perform(delete("/api/v1/products/1"))
                .andExpect(status().isOk());
    }

    @Test
    void get404OnDeleteProductWithWrongId() throws Exception {
        mockMvc.perform(delete("/api/v1/products/delete/100500"))
                .andExpect(status().isNotFound());
    }
}