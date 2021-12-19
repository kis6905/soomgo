package com.shop.domain.product;

import java.util.ArrayList;
import java.util.List;

import com.shop.infrastructure.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 도메인 영역으로 주요 로직을 담당하는 class 이다.
 * 인프라 역영이 변경되어도 도메인 영역은 영향이 없다.
 */
@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public List<Product> getProducts(String productType) {
    List<Product> products = productRepository.findAll();
    List<Product> result = new ArrayList<>();
    for (Product product : products) {
      // 슈퍼클래스 메서드의 호출
      // Bottom의 경우 decideRecommend를 오버라이드하지 않았기 때문에 화이트박스 재사용이다.
      product.decideRecommend();

      // interface를 통한 다중 상속
      // Discountable 인터페이스를 구현한 클래스일때만 discount()를 호출해 할인된 가격을 적용한다.
      if (product instanceof Discountable) {
        ((Discountable) product).discount(10);
      }

      // 공통 영역의 재사용과 확장 영역의 분리
      if (productType.equals("top") && (product instanceof Top)) {
        result.add(product);
      } else if (productType.equals("bottom") && (product instanceof Bottom)) {
        result.add(product);
      }
    }
    return result;
  }

}
