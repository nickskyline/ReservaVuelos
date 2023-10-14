package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;

public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aerolinea_generador")
    @SequenceGenerator(name = "aerolinea_generador", allocationSize = 1)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "pais")
    private String pais;

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_aerolinea")
    private Vuelo vuelo;
}
