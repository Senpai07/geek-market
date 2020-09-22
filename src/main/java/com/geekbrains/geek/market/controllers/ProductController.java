package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam(defaultValue = "0", name = "min") Integer min,
                                  @RequestParam(defaultValue = "0", name = "max") Integer max) {
        if (page < 1) page = 1;
        if ((min == 0) && (max == 0)) {
            model.addAttribute("products", productService.findAll(page - 1, 5));
        }
        if ((min > 0) && (max == 0)) {
            model.addAttribute("products", productService.findByPriceIsGreaterThanEqual(min));
        }
        if ((min == 0) && (max > 0)) {
            model.addAttribute("products", productService.findByPriceIsLessThanEqual(max));
        }
        if ((min > 0) && (max > 0)) {
            model.addAttribute("products", productService.findByPriceIsGreaterThanAndPriceIsLessThan(min, max));
        }
        return "products";
    }

}
