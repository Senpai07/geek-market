package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showOrders(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Specification<Order> spec = Specification.where(null);
        spec = spec.and((Specification<Order>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("user"), user.getId()));
        model.addAttribute("orders", orderService.findAll(spec));
        return "orders";
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "create_order";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmOrder(Principal principal, @RequestParam(name = "address") String address,
                              @RequestParam(name = "receiver_name") String receiverName,
                              @RequestParam(name = "phone") String phone
                              ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address, phone, receiverName);
        order = orderService.save(order);
        return "Ваш заказ №"+ order.getId();
    }
}
