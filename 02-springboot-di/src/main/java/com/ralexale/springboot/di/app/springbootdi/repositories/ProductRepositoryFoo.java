package com.ralexale.springboot.di.app.springbootdi.repositories;

import com.ralexale.springboot.di.app.springbootdi.models.Product;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("productList")
public class ProductRepositoryFoo implements ProductRepository {

  @Override
  public List<Product> findAll() {
    return Collections.singletonList(new Product(1L, "Monitor Asus", 100));
  }

  @Override
  public Product findById(Long id) {
    return new Product(id, "Monitor Asus", 100);
  }
}
