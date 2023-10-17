package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pasajero_generador")
    @SequenceGenerator(name = "pasajero_generador", allocationSize = 1)
    private Long id;
    @Column(name = "tipo_pasajero", nullable = false)
    private String tipoPasajero;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
    @ManyToMany(mappedBy = "pasajeros")
    private List<Reserva> reservas;

}
