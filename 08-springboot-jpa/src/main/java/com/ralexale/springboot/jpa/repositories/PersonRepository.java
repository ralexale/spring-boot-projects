package com.ralexale.springboot.jpa.repositories;

import com.ralexale.springboot.jpa.springbootjpa.entities.Person;
// import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// public interface PersonRepository extends JpaRepository<Person, String> {}
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {

    /*
     * Esta consulta funciona en base al nombre del método, tenemos que
     * colocar un nombre que coincida con los atributos que tenemos en nuestra
     * entidad y tiene que llevar el (findBy)
     */
    public List<Person> findByProgrammingLanguage(String programmingLanguage);

    public List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    /*
     * Para escribir una consulta personalizada tenemos
     * que colocar el nombre de la entidad con una abreviatura
     * con esta query estamos obteniendo todos las columnas
     * 
     * @Query("select p from Person p")
     */
    // @Query("select p from Person p")
    // @Query("select p from Person p where p.lastname = ?1 and p.name =?2")
    @Query("select p from Person p where p.lastname = ?1")
    public List<Person> buscarPorApellido(String lastname);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonValue();

}
