package com.ralexale.springboot.springbootjparelations.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ralexale.springboot.springbootjparelations.entities.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {

}
