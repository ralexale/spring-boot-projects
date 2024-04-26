package com.ralexale.springboot.springbootjparelations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String description;
  private Double total;

  @ManyToOne
  @JoinColumn(name = "client_id")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Client client;
}
