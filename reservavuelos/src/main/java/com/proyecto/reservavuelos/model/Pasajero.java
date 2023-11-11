package com.proyecto.reservavuelos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_pasajero", nullable = false)
    private String tipoPasajero;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
    @OneToMany(mappedBy = "pasajeros")
    @JsonIgnore
    private List<Reserva> reservas;
}