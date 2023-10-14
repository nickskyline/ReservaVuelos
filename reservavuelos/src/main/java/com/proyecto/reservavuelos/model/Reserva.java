package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserva_generador")
    @SequenceGenerator(name = "reserva_generador", allocationSize = 1)
    public Long id;


    @Column(name = "id_pasajero")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Long id_vuelo;

    @Column(name = "id_vuelo")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Long id_pasajero;

    @Column(name = "fecha_reservacion")
    private LocalDate fechaReserva;
}
