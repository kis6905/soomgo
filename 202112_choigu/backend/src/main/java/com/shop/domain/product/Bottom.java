package com.shop.domain.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Product의 하위클래스
 */
@Entity
@DiscriminatorValue("bottom")
public class Bottom extends Product implements Discountable, Orderable {

  public Bottom() {
    super();
  }
  
  public Bottom(String name, int price, int quantity) {
    super(name, price, quantity);
  }

  @Override
  public void discount(int rate) {
    super.setRate(rate);
    int price = super.getPrice();
    price = price - (price / rate);
    super.setPrice(price);
  }

  @Override
  public boolean isOrderable() {
    return super.getQuantity() > 0;
  }
}
