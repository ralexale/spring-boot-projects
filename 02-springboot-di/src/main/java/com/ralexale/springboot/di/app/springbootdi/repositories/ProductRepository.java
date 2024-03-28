package com.ralexale.springboot.di.app.springbootdi.repositories;

import com.ralexale.springboot.di.app.springbootdi.models.Product;
import java.util.List;

public interface ProductRepository {
  List<Product> findAll();
  Product findById(Long id);
}
