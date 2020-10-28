package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public Category save(Category category) {
        return categoryRepository.save(category);
    }

}
