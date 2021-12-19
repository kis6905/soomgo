package com.shop.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shop.domain.product.Product;

/**
 * 주문을 담당하는 데이터 클래스
 */
@Entity
@Table(name = "orders")
public class Order {
  @Id @GeneratedValue
  private int id; // DB Table의 PK이다
    public int getId() {
      return id;
    }

  @Column
  private int amount; // 주문 금액
    public int getAmount() {
      return amount;
    }
    public void setAmount(int amount) {
      this.amount = amount;
    }

  @Column
  private int quantity; // 주문 수량
    public int getQuantity() {
      return quantity;
    }
    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

  @ManyToOne // Product와 foreign key로 연결된다. JPA 설정이다.
  private Product product; // 주문한 상품
    public Product getProduct() {
      return product;
    }
    public void setProduct(Product product) {
      this.product = product;
    }

}
