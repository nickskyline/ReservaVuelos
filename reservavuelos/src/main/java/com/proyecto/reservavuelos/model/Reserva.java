package com.proyecto.reservavuelos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDate fechaReserva;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pasajero", referencedColumnName = "id")
    @JsonBackReference
    private Pasajero pasajeros;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_vuelo", referencedColumnName = "id")
    @JsonBackReference
    private Vuelo vuelo;

    public Reserva(LocalDate fechaReserva, Pasajero pasajeros, Vuelo vuelo) {
        this.fechaReserva = fechaReserva;
        this.pasajeros = pasajeros;
        this.vuelo = vuelo;
    }
}
