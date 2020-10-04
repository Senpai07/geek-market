package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String firstRequest(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @PostMapping("/save")
    public String saveOrder(@RequestParam String name, @RequestParam String address, @RequestParam String phone) {
        Order order = new Order();
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setItems(cart.getItems());
        orderService.addOrSaveOrder(order);
        cart.clearCart();
        return "redirect:/cart";
    }
}
