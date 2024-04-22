package com.ralexale.springboot.jpa.springbootjpa.entities;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
/*
 * Esta anotación es opcional, si no se coloca el nombre por defecto de
 * la tabla va a ser el nombre de la clase
 */
@Table(name = "person")
public class Person {

  @Id
  // Con esta anotación podemos definir como se van a crear los ids
  // IDENTITY es auto Incremental en MySQL
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String lastname;

  @Column(name = "programming_language")
  private String programmingLanguage;

  @Embedded
  @Autowired
  private Audit audit;

  // siempre que estemos en una clase con la anotación @Entity
  // tenemos que crear un constructor vacío
  public Person() {
  }

  public Person(
      String id,
      String name,
      String lastname,
      String programmingLanguage) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.programmingLanguage = programmingLanguage;
  }

  public Person(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getProgrammingLanguage() {
    return programmingLanguage;
  }

  public void setProgrammingLanguage(String programmingLanguage) {
    this.programmingLanguage = programmingLanguage;
  }

  @Override
  public String toString() {
    return ("Person [id=" +
        id +
        ", name=" +
        name +
        ", lastname=" +
        lastname +
        ", programmingLanguage=" +
        programmingLanguage +
        "create at=" +
        audit.getCreateAt() +
        ", update at=" +
        audit.getUpdateAt() +
        "]");
  }

}
