package com.ralexale.springboot.di.invoice.springbootinvoice;

import com.ralexale.springboot.di.invoice.springbootinvoice.models.Item;
import com.ralexale.springboot.di.invoice.springbootinvoice.models.Product;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

  @Bean
  //   @Primary
  List<Item> itemsInvoce() {
    Product p1 = new Product("Cámara Sony", 800);
    Product p2 = new Product("Iphone 15", 2000);
    Product p3 = new Product("Samsung Oled Tv 42", 1500);

    return Arrays.asList(new Item(p1, 2), new Item(p2, 1), new Item(p3, 4));
  }

  @Bean("default")
  List<Item> itemsInvoceOffice() {
    Product p1 = new Product("Cpu dell", 100);
    Product p2 = new Product("Ergonomic mouse", 23.99);
    Product p3 = new Product("Box pens", 10.99);
    Product p4 = new Product("Notebook Lenovo", 1800);
    Product p5 = new Product("Chair office", 50.99);

    return Arrays.asList(
      new Item(p1, 2),
      new Item(p2, 1),
      new Item(p3, 4),
      new Item(p4, 3),
      new Item(p5, 10)
    );
  }
}
