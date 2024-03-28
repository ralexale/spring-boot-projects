package com.ralexale.springboot.error.springbooterror.models.domain;

public class User {

  private String id;
  private String name;
  private String lastname;
  private Role role;

  public User() {}

  public User(String id, String name, String lastname) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
  }

  public User(String id, String name, String lastname, Role role) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.role = role;
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

  // public Role getRole() {
  //   return role;
  // }

  public String getRoleName() {
    return role.getName();
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
