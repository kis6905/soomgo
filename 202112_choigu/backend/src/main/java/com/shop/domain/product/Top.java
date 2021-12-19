package com.shop.domain.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Product의 하위클래스
 */
@Entity
@DiscriminatorValue("top")
public class Top extends Product implements Orderable {

  public Top() {
    super();
  }

  public Top(String name, int price, int quantity) {
    super(name, price, quantity);
  }

  // 오버라이드를 통한 행위 재정의
  @Override
  public void decideRecommend() {
    super.setRecommend(super.getQuantity() < 10);
  }

  // 오버라이드를 통한 행위 재정의
  @Override
  public boolean isOrderable() {
    return super.getQuantity() > 0;
  }
}
