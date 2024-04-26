package com.ralexale.springboot.springbootjparelations.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ralexale.springboot.springbootjparelations.entities.ClientDetails;

public interface ClientDetailsRepository extends CrudRepository<ClientDetails, String> {

}
