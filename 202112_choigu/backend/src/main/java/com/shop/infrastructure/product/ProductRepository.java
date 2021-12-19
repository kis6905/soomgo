package com.shop.infrastructure.product;

import java.util.List;

import javax.annotation.PostConstruct;

import com.shop.domain.product.Bottom;
import com.shop.domain.product.Product;
import com.shop.domain.product.Top;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 헥사고날 아키텍쳐에 의하면 도메인 영역과 인프라 영역간의 port 역할을 하는 class 이다.
 * 인프라가 변경되어도 도메인 영역은 변경하지 않아도 된다.
 *   - 인프라 변경 예) H2 DB -> mongoDB 또는 H2 DB -> mysql 와 같이 DB가 변경되는 경우
 */
@Repository
public class ProductRepository {

  @Autowired
  ProductH2Repository productH2Repository; // H2 DB를 사용

  // 초기 데이터 세팅
  @PostConstruct
  public void initData() {
    productH2Repository.save(new Top("티셔츠", 25000, 50));
    productH2Repository.save(new Top("블라우스", 41000, 20));
    productH2Repository.save(new Top("셔츠", 28000, 5));

    productH2Repository.save(new Bottom("청바지", 35000, 30));
    productH2Repository.save(new Bottom("면바지", 30000, 25));
    productH2Repository.save(new Bottom("스커트", 45000, 25));
  }

  // DB 에서 상품 목록 조회
  public List<Product> findAll() {
    return productH2Repository.findAll();
  }

  // id로 하나의 상품만 조회
  public Product findById(int id) {
    return productH2Repository.findById(id).orElse(null);
  }

  public Product save(Product product) {
    return productH2Repository.save(product);
  }

}
