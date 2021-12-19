package com.shop.application.product;

import java.util.List;

import com.shop.domain.product.Product;
import com.shop.domain.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @Autowired
  ProductService productService;

  // 상품 목록 url
  @RequestMapping(method = RequestMethod.GET, path = "shop/{type}/products")
	public List<Product> getProducts(@PathVariable String type) {
    // 상의/하의 구분을 type 으로 받는다.
		return productService.getProducts(type);
	}

}
