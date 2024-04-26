package com.ralexale.springboot.springbootjparelations;

import com.ralexale.springboot.springbootjparelations.entities.Address;
import com.ralexale.springboot.springbootjparelations.entities.Client;
import com.ralexale.springboot.springbootjparelations.entities.ClientDetails;
import com.ralexale.springboot.springbootjparelations.entities.Invoice;
import com.ralexale.springboot.springbootjparelations.repositories.ClientDetailsRepository;
import com.ralexale.springboot.springbootjparelations.repositories.ClientRepository;
import com.ralexale.springboot.springbootjparelations.repositories.InvoiceRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private InvoiceRepository invoiceRepository;

  @Autowired
  private ClientDetailsRepository clientDetailsRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // manyToOneCreateClientAndInvoice();
    // manyToOneFindByIdClient();

    // oneToMany();
    // oneToManyFindById();
    // oneToManyDelete();

    oneToOne();
  }

  @Transactional
  public void oneToOne() {
    Client client = new Client("Erba", "Pura");
    clientRepository.save(client);

    ClientDetails clientDetails = new ClientDetails(true, 1000);

    clientDetails.setClient(client);
    clientDetailsRepository.save(clientDetails);
  }

  @Transactional
  public void manyToOneBidireccional() {
    Client client = new Client("keiber", "Lazaro");
    clientRepository.save(client);

    Invoice invoice = new Invoice(null, "Teclado razor", 1000.00, null);
    Invoice invoice2 = new Invoice(null, "Mouse razor", 300.00, null);

    List<Invoice> invoices = Arrays.asList(invoice, invoice2);
    client.setInvoices(invoices);
  }

  /*
   * En este método estamos creando el cliente y la factura de un producto
   */
  @Transactional
  public void manyToOneCreateClientAndInvoice() {
    Client client = new Client("keiber", "Lazaro");
    clientRepository.save(client);

    Invoice invoice = new Invoice(null, "Teclado razor", 1000.00, null);
    invoice.setClient(client);
    Invoice invoiceDb = invoiceRepository.save(invoice);

    System.out.println(invoiceDb);
  }

  /*
   * En este método buscamos un cliente que ya esta creado por el id
   * y le creamos la factura a ese cliente
   */
  @Transactional
  public void manyToOneFindByIdClient() {
    // Client client =
    // clientRepository.findById("1d963bf2-7901-4ab0-b573-599dd27efbe3").orElseThrow();
    Optional<Client> optionalClient = clientRepository.findById(
      "1d963bf2-7901-4ab0-b573-599dd27efbe3"
    );

    if (optionalClient.isPresent()) {
      Client client = optionalClient.orElseThrow();

      Invoice invoice = new Invoice(null, "Teclado razor", 1000.00, null);

      invoice.setClient(client);

      Invoice invoiceDb = invoiceRepository.save(invoice);

      System.out.println(invoiceDb);
    }
  }

  @Transactional
  public void oneToMany() {
    Client client = new Client("Felipe ", "Velez");

    Address address1 = new Address("El santuario 7", 32);
    Address address2 = new Address("EL santuario 8", 14);

    // client.setAddress(addresses);

    client.getAddress().add(address1);
    client.getAddress().add(address2);

    clientRepository.save(client);

    System.out.println(client);
  }

  @Transactional
  public void oneToManyFindById() {
    Optional<Client> optionalClient = clientRepository.findById(
      "0fed67d7-d948-472b-b0b8-23a6173084ed"
    );

    optionalClient.ifPresent(client -> {
      Address address1 = new Address("El bogotá 3", 32);
      Address address2 = new Address("EL bogotá 4", 14);

      // List<Address> addresses = Arrays.asList(address1, address2);
      // client.setAddress(addresses);

      client.getAddress().add(address1);
      client.getAddress().add(address2);

      clientRepository.save(client);

      System.out.println(client);
    });
  }

  @Transactional
  public void oneToManyDelete() {
    Optional<Client> optionalClient = clientRepository.findById(
      "0fed67d7-d948-472b-b0b8-23a6173084ed"
    );

    optionalClient.ifPresent(c -> {
      System.out.println(c);
    });
    // optionalClient.ifPresent(c -> {
    // // c.getAddress();

    // c.getAddress()
    // .removeIf(address ->
    // address.getId().equals("507f69bc-0314-4609-bade-634e433a7953"));

    // clientRepository.save(c);
    // System.out.println(c);
    // });

  }
}
