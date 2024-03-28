package com.ralexale.springboot.di.app.springbootdi;

import com.ralexale.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.ralexale.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:appConfig.properties")
public class AppConfig {

  @Value("classpath:json/product.json")
  private Resource resource;

  // aca estamos creando un componente de spring
  @Bean("productJson")
  ProductRepository productRepositoryJson() {
    return new ProductRepositoryJson(resource);
  }
}
