package com.ralexale.springboot.springbootjparelations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.ralexale.springboot.springbootjparelations.entities.Address;
import com.ralexale.springboot.springbootjparelations.entities.Client;
import com.ralexale.springboot.springbootjparelations.entities.Invoice;
import com.ralexale.springboot.springbootjparelations.repositories.ClientRepository;
import com.ralexale.springboot.springbootjparelations.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationsApplication implements CommandLineRunner {
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// manyToOneCreateClientAndInvoice();
		// manyToOneFindByIdClient();
		// oneToMany();
		// oneToManyFindById();
		oneToManyDelete();
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
		Optional<Client> optionalClient = clientRepository.findById("1d963bf2-7901-4ab0-b573-599dd27efbe3");

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
		Client client = new Client("Hector ", "Montaña");

		Address address1 = new Address("El medellín 7", 32);
		Address address2 = new Address("EL medellín 8", 14);

		// client.setAddress(addresses);

		client.getAddress().add(address1);
		client.getAddress().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToManyFindById() {

		Optional<Client> optionalClient = clientRepository.findById("31c7df64-03a6-498c-93d1-76f707368900");

		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El medellín 3", 32);
			Address address2 = new Address("EL medellín 4", 14);

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
		Optional<Client> optionalClient = clientRepository.findById("026d4db7-d072-4e35-8ad6-b8d6371d51d2");

		optionalClient.ifPresent(c -> {
			// c.getAddress();

			c.getAddress()
					.removeIf(address -> address.getId().equals("f6251d02-6b9a-4de5-8542-69acfcdd5701"));

			clientRepository.save(c);
			System.out.println(c);
		});

	}
}
