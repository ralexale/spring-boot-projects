package com.ralexale.springboot.jpa.springbootjpa;

import com.ralexale.springboot.jpa.dto.PersonDto;
import com.ralexale.springboot.jpa.repositories.PersonRepository;
import com.ralexale.springboot.jpa.springbootjpa.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

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

    // list();
    // findOne();
    // create();
    update();
    // personalizedQuerys();
    // personalizedQuerys2();
    // personalizedQuerysDistinc();
    // personalizedQuerysConcatUpperAndLowerCase();
    // personalizedQuerysBetween();
    // personalizedSubQuerys();
    // personalizedQuerysWhereIn();
  }

  @Transactional
  public void update() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("integre el id de la persona a editar");
    String id = scanner.next();

    Optional<Person> optionalPerson = repository.findById(id);

    optionalPerson.ifPresent(person -> {
      System.out.println(person);

      System.out.println("ingresa el lenguaje de programación del coder");
      String programingLanguage = scanner.next();

      person.setProgrammingLanguage(programingLanguage);

      repository.save(person);
    });

    scanner.close();

  }

  @Transactional(readOnly = true)
  public void findOne() {
    // Person person = null;
    // Optional<Person> optionalPerson =
    // repository.findById("6a37c4b8-e30e-4b13-b9a5-4986cbcc5634");

    // if (optionalPerson.isPresent()) {
    // person = optionalPerson.get();
    // }

    // System.out.println(person);

    // List<Object[]> personValue = (List<Object[]>)
    // repository.obtenerPersonValue();

    // repository.findById("6a37c4b8-e30e-4b13-b9a5-4986cbcc5634").ifPresent(System.out::println);

    // repository.findOneLikeName("An").ifPresent(System.out::println);

    repository.findByNameContaining("He").ifPresent(System.out::println);

    // repository.findOneByName("Hector").ifPresent(System.out::println);

    // personValue.stream()
    // .forEach(personValueTemp -> System.out.println(personValueTemp[0] + " experto
    // en " + personValueTemp[1]));
  }

  @Transactional(readOnly = true)
  public void personalizedQuerys() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("ingrese el id de la persona");
    String id = scanner.next();

    String name = this.repository.getNameById(id);
    String fullName = this.repository.getFullNameById(id);

    // Object[] personData = this.repository.getPersonDataById(id);

    System.out.println(name);
    System.out.println(fullName);
    // System.out.println("id = " + personData[0]);

    scanner.close();
  }

  @Transactional(readOnly = true)
  public void personalizedQuerys2() {
    System.out.println(
        "======================= consulta por objeto person y lenguaje de programación ======================= ");

    List<Object[]> personRegs = repository.findAllMix();

    personRegs.forEach(reg -> {
      System.out.println("programming language: " + reg[1] + " person = " + reg[0]);
    });

    System.out.println(
        "======================= consulta que puebla y devuelve objeto entity de una clase personalizada ======================= ");

    List<Person> persons = repository.findAllPersonalizedObjectPerson();

    persons.forEach(System.out::println);

    System.out.println(
        "======================= consulta que puebla y devuelve objeto PersonDto de una clase DTO personalizada ======================= ");

    List<PersonDto> personDto = repository.findAllPersonDto();
    personDto.forEach(System.out::println);

  }

  public void personalizedQuerysConcatUpperAndLowerCase() {

    List<String> nombresConcat = repository.getFullNamesConcat();

    nombresConcat.forEach(System.out::println);

    System.out.println("\n Nombres en mayúscula \n");
    List<String> nombresConcatUpper = repository.getFullNamesConcatUpper();
    nombresConcatUpper.forEach(System.out::println);

    System.out.println("\n Nombres en minúscula \n");
    List<String> nombresConcatLower = repository.getFullNamesConcatLower();

    nombresConcatLower.forEach(System.out::println);

  }

  public void personalizedQuerysDistinc() {

    // System.out.println("Consultas con nombres de personas");
    // List<String> nombres = repository.findAllNames();

    System.out.println("Consultas con nombres de personas");
    List<String> nombresDistintos = repository.findAllDistinctsNames();
    nombresDistintos.forEach(System.out::println);

    System.out.println("\n Consultas con lenguajes de programación unicos");

    List<String> lenguajesDistintos = repository.findAllDistinctsProgrammingLanguage();

    lenguajesDistintos.forEach(System.out::println);

    System.out.println("\n Consultas con total lenguajes de programación unicos");

    Long contadorDeLenguajes = repository.CountAllDistinctsProgrammingLanguage();

    System.out.println(contadorDeLenguajes);

  }

  public void personalizedQuerysBetween() {

    List<Person> personsB = repository.findPersonBetween();

    personsB.forEach(System.out::println);

  }

  @Transactional(readOnly = true)
  public void personalizedQuerysWhereIn() {
    List<Person> persons = repository.getPersonsByIds();

    persons.forEach(System.out::println);
  }

  public void personalizedSubQuerys() {
    List<Object[]> registers = repository.getShortedName();
    registers.forEach(reg -> {
      String name = (String) reg[0];
      Integer length = (Integer) reg[1];
      System.out.println("Name = " + name + " length = " + length);
    });

  }

  @Transactional
  public void create() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("ingresa el nombre del coder");
    String name = scanner.next();

    System.out.println("ingresa el apellido del coder");
    String lastname = scanner.next();

    System.out.println("ingresa el lenguaje de programación del coder");
    String programingLanguage = scanner.next();

    scanner.close();

    Person person = new Person(null, name, lastname, programingLanguage);

    Person personNew = repository.save(person);

    System.out.println(personNew);

    // método de referencia
    repository.findById(personNew.getId()).ifPresent(System.out::println);
  }

  @Transactional
  public void delete() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingresa el id de la persona");

    String id = scanner.next();

    this.repository.deleteById(id);

    scanner.close();
  }

  @Transactional(readOnly = true)
  public void list() {
    // esta método viene en el crud repository y nos permite obtener todas las
    // columnas de la tabla
    List<Person> persons = (List<Person>) repository.findAll();

    // creamos un método que nos permite hacer consultas para filtrar por lenguaje
    // de programación
    // List<Person> persons = (List<Person>)
    // repository.findByProgrammingLanguage("Java");

    // List<Person> persons = (List<Person>)
    // repository.findByProgrammingLanguageAndName("Java", "Jose");

    // List<Person> persons = (List<Person>) repository.buscarPorApellido("Velez");
    // List<Object> persons = repository.obtenerIdPersons();

    // Person person = (Person) repository
    // .findById("6a37c4b8-e30e-4b13-b9a5-4986cacc5634")
    // .orElseThrow();

    // Person personLanguage = (Person)
    // repository.findByProgrammingLanguage("Java");

    // System.out.println(personLanguage);

    persons.stream().forEach(personTemp -> System.out.println(personTemp));

    // System.out.println("find =>>" + person);
  }
}
