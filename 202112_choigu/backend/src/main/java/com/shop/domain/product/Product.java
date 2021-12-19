package com.shop.domain.product;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 도메인 영역에서 사용하는 Product 도메인의 공통 영역 데이터 클래스(상위 클래스)
 */
@Entity
@DiscriminatorColumn( // 상의/하의를 구분하기 위한 값이다.
    discriminatorType = DiscriminatorType.STRING,
    name = "product_type",
    columnDefinition = "VARCHAR(30)"
)
public class Product {
  @Id @GeneratedValue
  private int id; // DB Table의 PK이다
    public int getId() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
    }

  @Column
  private String name; // 상품 이름
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }

  @Column
  private int price; // 상품 가격
    public int getPrice() {
      return price;
    }
    public void setPrice(int price) {
      this.price = price;
    }

  @Column
  private int quantity; // 재고
    public int getQuantity() {
      return quantity;
    }
    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }

  private boolean recommend; // 추천상품인지 여부
    public boolean getRecommend() {
      return recommend;
    }
    public void setRecommend(boolean recommend) {
      this.recommend = recommend;
    }
    public void decideRecommend() {
      this.recommend = false;
    }

  private boolean orderable; // 주문가능한 상품인지 여부
    public boolean getOrderable() {
      return orderable;
    }
    public void setOrderable(boolean orderable) {
      this.orderable = orderable;
    }
    public void decideOrderable() {
      this.orderable = true;
    }

  private int rate; // 할인률, discount 할때 필요한 변수이다.
    public int getRate() {
      return rate;
    }
    public void setRate(int rate) {
      this.rate = rate;
    }

  public Product() {}

  public Product(String name, int price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

}
