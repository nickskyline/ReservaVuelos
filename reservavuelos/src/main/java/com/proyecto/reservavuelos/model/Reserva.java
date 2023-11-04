package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
