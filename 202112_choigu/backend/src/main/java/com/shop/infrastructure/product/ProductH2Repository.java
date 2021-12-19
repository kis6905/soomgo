package com.shop.infrastructure.product;

import java.util.List;

import com.shop.domain.product.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductH2Repository extends CrudRepository<Product, Integer> {
  @Override
  List<Product> findAll();
}
