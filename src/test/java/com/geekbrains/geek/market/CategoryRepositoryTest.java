package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void categoryRepositoryTest() {
        Category category = new Category();
        category.setTitle("Молочка");
        testEntityManager.persist(category);
        testEntityManager.flush();

        List<Category> categoriesList = categoryRepository.findAll();

        Assertions.assertEquals(3, categoriesList.size());
        Assertions.assertEquals("Молочка", categoriesList.get(1).getTitle());
    }

    @Test
    public void initDbTest() {
        List<Category> categoriesList = categoryRepository.findAll();
        Assertions.assertEquals(2, categoriesList.size());
    }
}
