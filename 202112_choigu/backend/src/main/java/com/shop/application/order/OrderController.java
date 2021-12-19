package com.shop.application.order;

import java.util.List;

import com.shop.domain.order.Order;
import com.shop.domain.order.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Autowired
  OrderService orderService;

  // 주문 목록 url
  @RequestMapping(method = RequestMethod.GET, path = "shop/orders")
	public List<Order> orders() {
		return orderService.getOrders();
	}

  // 주문 하기 url
  @RequestMapping(method = RequestMethod.GET, path = "shop/order")
	public String order(@RequestParam int productId, @RequestParam int quantity) {
		return orderService.order(productId, quantity);
	}

}
