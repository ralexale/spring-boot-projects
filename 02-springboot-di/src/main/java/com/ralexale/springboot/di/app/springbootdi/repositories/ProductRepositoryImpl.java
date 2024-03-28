package com.ralexale.springboot.di.app.springbootdi.repositories;

import com.ralexale.springboot.di.app.springbootdi.models.Product;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Primary
@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private List<Product> data;

  public ProductRepositoryImpl() {
    this.data =
      Arrays.asList(
        new Product(1L, "Razer Keyboard", 100),
        new Product(2L, "CPU INTEL I5 1200K", 500),
        new Product(3L, "MotherBoard Asus Gaming", 200),
        new Product(4L, "Memory RAM 16GB Corsair", 70)
      );
  }

  @Override
  public List<Product> findAll() {
    return data;
  }

  @Override
  public Product findById(Long id) {
    /*
     * usamos el stream.filter para filtrar el objeto que necesitemos
     * luego tenemos que encontrar el primero y luego podemos usar el método
     * get(), orElseThrow(), orElse(null)
     * la diferencia es que el orElseThrow nos va a lanzar un excepción si no encontrar el objeto
     * orElse nos va a retornar el valor que lo coloquemos en este caso null
     */
    return data
      .stream()
      .filter(p -> p.getId().equals(id))
      .findFirst()
      .orElseThrow();
  }
}
