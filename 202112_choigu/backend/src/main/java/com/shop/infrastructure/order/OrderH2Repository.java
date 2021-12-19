package com.shop.infrastructure.order;

import java.util.List;

import com.shop.domain.order.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderH2Repository extends CrudRepository<Order, Integer> {
  @Override
  List<Order> findAll();
}
