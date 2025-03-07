package com.shop.cafe.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController // request가 들어왔을때 맨 앞단에서 url을 라우팅할 수 있음
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	Map<String, Object> storage = new HashMap();

	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try {
			Object o = storage.get("firstPageProducts"); // firstPageProducts로 Object 찾기
			if(o == null) {
				List<Product> list = productService.getAllProducts();
				storage.put("firstPageProducts", list);
				return list;
			}
			
			return (List<Product>) o; // storage에 내용이 있을 경우 테스팅을 해서 return함
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
