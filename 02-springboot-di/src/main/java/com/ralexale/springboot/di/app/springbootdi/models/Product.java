package com.ralexale.springboot.di.app.springbootdi.models;

/*
 * Creamos el modelo del producto pero este va a implementar 
 * la interface Cloneable para que podamos clonar el producto
 * en caso de que necesitemos manejar la inmutabilidad
 */
public class Product implements Cloneable {

  private Long id;
  private String name;
  private double price;

  public Product() {}

  public Product(Long id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  /*
   * Sobreescribimos el método para cambiar como manejamos la excepción con un trycatch
   */
  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      return new Product(id, name, price);
    }
  }
}
