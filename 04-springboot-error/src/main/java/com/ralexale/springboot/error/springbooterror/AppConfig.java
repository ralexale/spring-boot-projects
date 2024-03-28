package com.ralexale.springboot.error.springbooterror;

import com.ralexale.springboot.error.springbooterror.models.domain.Role;
import com.ralexale.springboot.error.springbooterror.models.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

  @Bean
  @Primary
  List<User> usersWithoutRole() {
    List<User> users = new ArrayList<>();
    users.add(new User("1", "Alejandro", "Montaña", new Role("admin")));
    users.add(new User("2", "Andres", "Velez", new Role("user")));
    users.add(new User("3", "Juan", "Felipe"));
    users.add(new User("4", "Sebastian", "Moreno"));
    users.add(new User("5", "Diego", "Rojas"));
    return users;
  }

  @Bean
  List<User> usersWithRole() {
    List<User> users = new ArrayList<>();
    users.add(new User("1", "Alejandro", "Montaña", new Role("admin")));
    users.add(new User("2", "Andres", "Velez", new Role("user")));
    users.add(new User("3", "Juan", "Felipe", new Role("user")));
    users.add(new User("4", "Sebastian", "Moreno", new Role("user")));
    users.add(new User("5", "Diego", "Rojas", new Role("user")));
    return users;
  }
}
