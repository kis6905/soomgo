package com.shop.domain.order;

import java.util.List;

import com.shop.domain.product.Bottom;
import com.shop.domain.product.Discountable;
import com.shop.domain.product.Orderable;
import com.shop.domain.product.Product;
import com.shop.domain.product.Top;
import com.shop.infrastructure.order.OrderRepository;
import com.shop.infrastructure.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 도메인 영역으로 주요 로직을 담당하는 class 이다.
 * 인프라 역영이 변경되어도 도메인 영역은 영향이 없다.
 */
@Service
public class OrderService {
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  ProductRepository productRepository;

  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  public String order(int productId, int quantity) {
    Order order = new Order();

    // 주문하려는 상품을 DB에서 조회해 가격과 주문수량으로 총 금액을 계산한다.
    Product product = productRepository.findById(productId);
    order.setProduct(product);

    int originalPrice = product.getPrice();

    // interface를 통한 다중 상속
    // Discountable 인터페이스를 구현한 클래스일때만 discount 메소드를 호출해 할인된 가격을 적용한다.
    if (product instanceof Discountable) {
      ((Discountable) product).discount(10);
    }

    // Orderable 인터페이스를 구현한 클래스일때만 주문가능한 상품인지 isOrderable()를 호출해 확인한다.
    if (product instanceof Orderable && !((Orderable) product).isOrderable()) {
      return "주문할 수 없는 상품입니다.";
    }

    order.setQuantity(quantity);
    order.setAmount(product.getPrice() * quantity); // 총 금액 계산

    Order savedOrder = orderRepository.save(order); // 주문 정보 DB에 저장

    if (savedOrder != null) { // 주문 정보 DB 저장 성공
      // 주문이 성공하면 Product의 수량을 주문한 수량만큼 빼준다.
      
      Product updateProduct = null;
      if (product instanceof Top) { // 주문한 상품이 상의일땐 Top 객체를 만든다.
        updateProduct = new Top();
      } else if (product instanceof Bottom) { // 주문한 상품이 하의일땐 Bottom 객체를 만든다.
        updateProduct = new Bottom();
      }
      updateProduct.setId(product.getId());
      updateProduct.setName(product.getName());
      updateProduct.setPrice(originalPrice);
      updateProduct.setQuantity(product.getQuantity() - quantity); // 상품 수량 - 주문한 수량
      productRepository.save(updateProduct); // DB 저장
      return "주문이 성공했습니다.";
    } else {
      return "주문이 실패했습니다.";
    }
  }

}
