package com.ralexale.springboot.springbootjparelations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ralexale.springboot.springbootjparelations.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

}
