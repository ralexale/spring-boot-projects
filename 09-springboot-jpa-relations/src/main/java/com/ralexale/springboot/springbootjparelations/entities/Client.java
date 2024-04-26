package com.ralexale.springboot.springbootjparelations.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String name;
  private String lastname;

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.EAGER
  )
  // @JoinColumn(name = "client_id")
  @JoinTable(
    name = "tbl_clientes_to_direcciones",
    joinColumns = @JoinColumn(name = "id_cliente"),
    inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
    uniqueConstraints = @UniqueConstraint(columnNames = { "id_direcciones" })
  )
  private List<Address> address = new ArrayList<>();

  @OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    mappedBy = "client"
  )
  private List<Invoice> invoices = new ArrayList<>();

  public Client(String name, String lastname) {
    this.name = name;
    this.lastname = lastname;
  }
}
