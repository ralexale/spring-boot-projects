package com.ralexale.springboot.di.invoice.springbootinvoice.controllers;

import com.ralexale.springboot.di.invoice.springbootinvoice.models.Client;
import com.ralexale.springboot.di.invoice.springbootinvoice.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

  @Autowired
  private Invoice invoice;

  @GetMapping("/show")
  public Invoice show() {
    // creamos una nueva instancia de la factura para no devolver el proxy
    // porque tiene archivos residuales que no queremos
    Invoice i = new Invoice();
    Client c = new Client();

    c.setLastname(invoice.getClient().getLastname());
    c.setName(invoice.getClient().getName());

    i.setClient(c);
    i.setDescription(invoice.getDescription());
    i.setItems(invoice.getItems());

    return i;
  }
}
