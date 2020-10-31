package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping(produces = "application/json")
    public List<OrderDto> getOrderList(@RequestParam(name = "userName") String userName) {
        User user = userService.findByUsername(userName);
        Specification<Order> spec = Specification.where(null);
        spec = spec.and((Specification<Order>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("user"), user.getId()));
        List<Order> orderList = orderService.findAll(spec);
        return orderList.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping("/confirm")
    public OrderDto confirmOrder(@RequestParam(name = "userName") String userName,
                                 @RequestParam(name = "address") String address,
                                 @RequestParam(name = "receiverName") String receiverName,
                                 @RequestParam(name = "phone") String phone) {
        User user = userService.findByUsername(userName);
        Order order = new Order(user, cart, address, phone, receiverName);
        order = orderService.save(order);
        return new OrderDto(order);
    }
}
