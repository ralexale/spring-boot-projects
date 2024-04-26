package com.ralexale.springboot.springbootjparelations.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ralexale.springboot.springbootjparelations.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

    @Query("select c from Client c join fetch c.address where c.id = ?1 ")
    Optional<Client> findOne(String id);
}
