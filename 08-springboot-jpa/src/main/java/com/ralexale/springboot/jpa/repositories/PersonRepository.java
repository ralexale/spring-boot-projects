package com.ralexale.springboot.jpa.repositories;

import com.ralexale.springboot.jpa.springbootjpa.entities.Person;
// import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// public interface PersonRepository extends JpaRepository<Person, String> {}
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {}
