package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping(produces = "application/json")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

}
