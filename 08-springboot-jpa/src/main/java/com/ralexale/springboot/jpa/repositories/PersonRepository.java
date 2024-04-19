package com.ralexale.springboot.jpa.repositories;

import com.ralexale.springboot.jpa.dto.PersonDto;
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

    /*
     * Para hacer una consulta con el between usamos la palabra
     * resevada y creamos el método
     */
    public List<Person> findByNameBetween(String c1, String c2);

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

    /*
     * Concat nos sirve para juntar valores, también podemos usar || que hace lo
     * mismo
     */
    @Query("SELECT concat(p.name,' ', p.lastname) FROM Person p WHERE p.id =?1")
    String getFullNameById(String id);

    @Query("SELECT p.name || ' ' || p.lastname FROM Person p ")
    List<String> getFullNamesConcat();

    /*
     * Con el upper y lower podemos convertir los valores
     * a mayúscula y minúscula
     */
    @Query("SELECT upper(p.name || ' ' || p.lastname) FROM Person p ")
    List<String> getFullNamesConcatUpper();

    @Query("SELECT lower(concat(p.name, ' ', p.lastname)) FROM Person p ")
    List<String> getFullNamesConcatLower();

    @Query("SELECT p, p.programmingLanguage From Person p")
    List<Object[]> findAllMix();

    /*
     * Creamos una nueva instancia del person que solo va a tener el nombre
     * y el apellido
     */
    @Query("SELECT new Person(p.name, p.lastname) FROM Person p")
    List<Person> findAllPersonalizedObjectPerson();

    @Query("select new com.ralexale.springboot.jpa.dto.PersonDto(p.name , p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p.name From Person p")
    List<String> findAllNames();

    /*
     * Usamos el distinct para traer todo los nombres que sean
     * diferentes si hay alguno repetido no va a salir en la lista
     */
    @Query("select distinct(p.name) From Person p")
    List<String> findAllDistinctsNames();

    @Query("select distinct(p.programmingLanguage) From Person p")
    List<String> findAllDistinctsProgrammingLanguage();

    /*
     * Podemos usar el count para traer el numero total de resultados
     */
    @Query("select count(distinct(p.programmingLanguage)) From Person p")
    Long CountAllDistinctsProgrammingLanguage();

    @Query("Select p From Person p where p.name between 'A' and 's' ")
    List<Person> findPersonBetween();

    /*
     * Vamos a obtener un valor haciendo una subconsulta
     */

    @Query("select p.name, length(p.name) from Person p where length(p.name) = (select min(length(p.name)) From Person p)")
    List<Object[]> getShortedName();

}
