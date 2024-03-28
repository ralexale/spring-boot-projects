package com.ralexale.springboot.di.invoice.springbootinvoice.models;

// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
// import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
// @ApplicationScope
// @JsonIgnoreProperties({ "advisors", "targetSource" })
public class Invoice {

  @Autowired
  private Client client;

  @Value("${invoice.description}")
  private String description;

  @Autowired
  @Qualifier("default")
  private List<Item> items;

  public Invoice() {
    System.out.println("creando el componente de la factura");
    System.out.println(client);
    System.out.println(description);
  }

  @PostConstruct
  public void init() {
    System.out.println("creando el componente de la factura");

    client.setName(client.getName().concat(" pepe"));
    description =
      description
        .concat(" del cliente: ")
        .concat(client.getName())
        .concat(" ")
        .concat(client.getLastname());
  }

  @PreDestroy
  public void destroy() {
    System.out.println("Destruyendo el componente o bean invoice!");
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public double getTotal() {
    // Double total = items
    //   .stream()
    //   .map(item -> item.getTotalProduct())
    //   .reduce(0.0, (sum, totalP) -> sum + totalP);

    // Double total = items
    //     .stream()
    //     .mapToDouble(Item::getTotalProduct)
    //     .reduce(0.0, (sum, totalP) -> sum + totalP);

    return items.stream().mapToDouble(Item::getTotalProduct).sum();
  }
}
