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
@EnableJpaRepositories(basePackages = "com.ralexale.springboot.jpa.repositories")
public class SpringbootJpaApplication implements CommandLineRunner {

  @Autowired
  private PersonRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // esta método viene en el crud repository y nos permite obtener todas las
    // columnas de la tabla
    // List<Person> persons = (List<Person>) repository.findAll();

    // creamos un método que nos permite hacer consultas para filtrar por lenguaje
    // de programación
    // List<Person> persons = (List<Person>)
    // repository.findByProgrammingLanguage("Java");

    List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Jose");

    List<Object[]> personValue = (List<Object[]>) repository.obtenerPersonValue();
    // List<Person> persons = (List<Person>) repository.buscarPorApellido("Velez");

    // Person person = (Person) repository
    // .findById("6a37c4b8-e30e-4b13-b9a5-4986cacc5634")
    // .orElseThrow();

    // Person personLanguage = (Person)
    // repository.findByProgrammingLanguage("Java");

    // System.out.println(personLanguage);
    persons.stream().forEach(personTemp -> System.out.println(personTemp));
    personValue.stream()
        .forEach(personValueTemp -> System.out.println(personValueTemp[0] + " experto en " + personValueTemp[1]));
    // System.out.println("find =>>" + person);
  }
}
