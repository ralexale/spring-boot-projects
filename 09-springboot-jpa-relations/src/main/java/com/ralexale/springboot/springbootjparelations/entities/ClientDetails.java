package com.ralexale.springboot.springbootjparelations.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private boolean premium;
    private Integer points;

    @OneToOne
    private Client client;

    public ClientDetails(boolean premium, Integer points) {
        this.premium = premium;
        this.points = points;
    }

}
