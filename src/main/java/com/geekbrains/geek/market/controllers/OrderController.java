package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final Cart cart;

//    @GetMapping(produces = "application/json")
//    public List<OrderDto> getOrderList(Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        Specification<Order> spec = Specification.where(null);
//        spec = spec.and((Specification<Order>) (root, criteriaQuery, criteriaBuilder) ->
//                criteriaBuilder.greaterThanOrEqualTo(root.get("user"), user.getId()));
//        List<Order> orderList = orderService.findAll(spec);
//        return orderList.stream().map(OrderDto::new).collect(Collectors.toList());
//    }

    @GetMapping(produces = "application/json")
    public List<OrderDto> getUserOrders(Principal principal) {
        return orderService.findAllUserOrdersDtoByUsername(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestParam String address, @RequestParam String phone, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to create order for user: " + principal.getName() + ". User doesn't exist!"));
        Order order = new Order(user, cart, address, phone, principal.getName());
        orderService.save(order);
        cart.clear();
    }

    @PostMapping("/confirm")
    public OrderDto confirmOrder(@RequestParam(name = "address") String address,
                                 @RequestParam(name = "phone") String phone, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to confirm order for user: " + principal.getName() + ". User doesn't exist!"));
        Order order = new Order(user, cart, address, phone, principal.getName());
        order = orderService.save(order);
        return new OrderDto(order);
    }
}
