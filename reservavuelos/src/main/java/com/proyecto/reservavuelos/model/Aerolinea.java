package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL)
    private List<Vuelo> vuelos;

}
