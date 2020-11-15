package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.OrderEntity;
import com.geekbrains.geek.market.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> findAll(Specification<OrderEntity> spec) {
        return orderRepository.findAll(spec);
    }

    public List<OrderDto> findAllUserOrdersDtoByUsername(String username) {
        return orderRepository.findAllOrdersByUsername(username).stream().map(OrderDto::new).collect(Collectors.toList());
    }


    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

}
