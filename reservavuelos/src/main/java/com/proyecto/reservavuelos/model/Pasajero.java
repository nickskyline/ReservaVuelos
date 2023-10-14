package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;

public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pasajero_generador")
    @SequenceGenerator(name = "pasajero_generador", allocationSize = 1)
    private Long id;

    @Column(name = "id_persona")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Long id_persona;
    @Column(name = "tipo_pasajero")
    private String tipoPasajero;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pasajero")
    private Reserva reserva;
}
