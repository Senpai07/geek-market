package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params) {
        if (page < 1) page = 1;
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
        return "products";
    }

    @GetMapping("/edit")
    public String editProducts(Model model, @RequestParam Long id) {
        Optional<Product> product = productService.findById(id);
        model.addAttribute("product", product);
        return "edit_product";
    }

    @GetMapping("/save")
    public String saveProducts(Model model, @RequestParam Long id, @RequestParam String title, @RequestParam int price) {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setPrice(price);
        productService.saveProduct(product);
        return "redirect:/products";
    }

}
