package com.ralexale.springboot.jpa.repositories;

import com.ralexale.springboot.jpa.springbootjpa.entities.Person;
// import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

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

    public Optional<Person> findByName(String name);

    /*
     * Para hacer una busqueda con un like tenemos que escribir el nombre del
     * método con Containing
     */
    public Optional<Person> findByNameContaining(String name);

    @SuppressWarnings("null")
    public Optional<Person> findById(String id);

    /*
     * Para escribir una consulta personalizada tenemos
     * que colocar el nombre de la entidad con una abreviatura
     * con esta query estamos obteniendo todos las columnas
     * 
     * @Query("select p from Person p")
     */
    // @Query("select p from Person p")
    @Query("select p from Person p where p.lastname = ?1 and p.name =?2")
    // @Query("select p from Person p where p.lastname = ?1 and p.")
    public List<Person> buscarPorApellido(String lastname, String name);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    // cuando hacemos un select de esta manera se retorna los valores como una lista
    // donde el primer valor p.name va a estar en el indice 0 y
    // p.programmingLanguage
    // va a estar en el indice 1
    List<Object[]> obtenerPersonValue();

    @Query("SELECT p.id FROM Person p")
    // como traemos un solo atributo esto retorna una list de un objeto
    List<Object> obtenerIdPersons();

    @Query("SELECT p.id, p.name, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Object[] getPersonDataById(String id);

    @Query("SELECT p FROM Person p WHERE p.name like %?1%  ORDER BY p.name limit 1 ")
    Optional<Person> findOneLikeName(String name);

    @Query("SELECT p FROM Person p WHERE p.name like %?1%")
    List<Person> findLikeName(String name);

    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneByName(String name);

    @Query("SELECT p.name FROM Person p WHERE p.id =?1")
    String getNameById(String id);

    @Query("SELECT concat(p.name,' ', p.lastname) FROM Person p WHERE p.id =?1")
    String getFullNameById(String id);

}
