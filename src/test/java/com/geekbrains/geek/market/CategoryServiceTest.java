package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import com.geekbrains.geek.market.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void findOneCategoryTest() {
        Category categoryFromDb = new Category();
        categoryFromDb.setTitle("Молочка");

        Mockito.doReturn(Optional.of(categoryFromDb)).when(categoryRepository).findByTitle("Молочка");

        Category categoryMilk = categoryService.findByTitle("Молочка").get();

        Assertions.assertNotNull(categoryMilk);

        Assertions.assertEquals("Молочка", categoryMilk.getTitle());

        Mockito.verify(categoryRepository, Mockito.times(1))
                .findByTitle(ArgumentMatchers.eq("Молочка"));
    }

}
