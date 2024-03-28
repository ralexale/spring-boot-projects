package com.ralexale.springboot.di.app.springbootdi.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ralexale.springboot.di.app.springbootdi.models.Product;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ProductRepositoryJson implements ProductRepository {

  private List<Product> list;

  // el value no funciona si no esta adentro de un componente de spring
  // @Value("classpath:json/product.json")
  // private Resource resource;

  public ProductRepositoryJson() {
    // ClassPathResource me permite leer los archivos afuera de mi aplicación
    Resource resource = new ClassPathResource("json/product.json");
    // nos permite convertir un file a un objeto de java
    readValueJson(resource);
  }

  public ProductRepositoryJson(Resource resource) {
    readValueJson(resource);
  }

  private void readValueJson(Resource resource) {
    // ClassPathResource me permite leer los archivos afuera de mi aplicación
    // Resource resource = new ClassPathResource("json/product.json");
    // nos permite convertir un file a un objeto de java
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      list =
        Arrays.asList(
          objectMapper.readValue(resource.getFile(), Product[].class)
        );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Product> findAll() {
    return list;
  }

  @Override
  public Product findById(Long id) {
    return list
      .stream()
      .filter(p -> p.getId().equals(id))
      .findFirst()
      .orElseThrow();
  }
}
