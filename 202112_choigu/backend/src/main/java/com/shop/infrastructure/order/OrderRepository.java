package com.shop.infrastructure.order;

import java.util.List;

import com.shop.domain.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 헥사고날 아키텍쳐에 의하면 도메인 영역과 인프라 영역간의 port 역할을 하는 class 이다.
 * 인프라가 변경되어도 도메인 영역은 변경하지 않아도 된다.
 *   - 인프라 변경 예) H2 -> mongoDB, H2 -> mysql
 */
@Repository
public class OrderRepository {

  @Autowired
  OrderH2Repository orderH2Repository; // H2 DB를 사용

  // DB 에서 주문 목록 조회
  public List<Order> findAll() {
    return orderH2Repository.findAll();
  }

  // DB에 주문 저장
  public Order save(Order order) {
    return orderH2Repository.save(order);
  }

}
