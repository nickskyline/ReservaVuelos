package com.proyecto.reservavuelos.model;

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
    @ManyToMany(mappedBy = "pasajeros")
    private List<Reserva> reservas;
}
