package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_generador")
    @SequenceGenerator(name = "reserva_generador", allocationSize = 1)
    public Long id;


    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDate fechaReserva;

    @ManyToMany
    @JoinTable(
            name = "reserva_pasajero",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_pasajero")
    )
    private List<Pasajero> pasajeros;

    @ManyToMany
    @JoinTable(
            name = "reserva_vuelo",
            joinColumns = @JoinColumn(name = "id_reserva"),
            inverseJoinColumns = @JoinColumn(name = "id_vuelo")
    )
    private List<Vuelo> vuelos;
}
