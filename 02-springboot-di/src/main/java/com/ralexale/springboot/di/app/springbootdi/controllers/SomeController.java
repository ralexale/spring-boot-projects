package com.ralexale.springboot.di.app.springbootdi.controllers;

import com.ralexale.springboot.di.app.springbootdi.models.Product;
import com.ralexale.springboot.di.app.springbootdi.services.ProductServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SomeController {

  /*
   * tenemos un problema al llamar el ProductService de esta manera
   * estamos mutando los productos que tenemos, cada vez que hacemos una request
   * aumentamos el precio del producto debeido a la multiplicación que tenemos en el service
   * la ideal seria que en cada escope de nuestros metodos tener una instancia del ProductService
   *
   * @GetMapping
   * public List<Product> list() {
   * private ProductService service = new ProductService();
   *   return service.findAll();
   * }
   *
   * Pero esto no es una buena practica porque estariamos repitiendo código
   * esto lo arreglamos en el ProductService
   */
  @Autowired
  private ProductServiceImpl service;

  @GetMapping
  public List<Product> list() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public Product showProduct(@PathVariable Long id) {
    return service.findById(id);
  }
}
