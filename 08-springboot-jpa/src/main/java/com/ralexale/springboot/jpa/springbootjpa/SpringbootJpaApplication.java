package com.ralexale.springboot.jpa.springbootjpa;

import com.ralexale.springboot.jpa.repositories.PersonRepository;
import com.ralexale.springboot.jpa.springbootjpa.entities.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
  basePackages = "com.ralexale.springboot.jpa.repositories"
)
public class SpringbootJpaApplication implements CommandLineRunner {

  @Autowired
  private PersonRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    List<Person> persons = (List<Person>) repository.findAll();
    Person personfind = (Person) repository
      .findById("6a37c4b8-e30e-4b13-b9a5-4986cacc5634")
      .orElseThrow();

    persons.stream().forEach(person -> System.out.println(person));
    System.out.println("find =>>" + personfind);
  }
}
