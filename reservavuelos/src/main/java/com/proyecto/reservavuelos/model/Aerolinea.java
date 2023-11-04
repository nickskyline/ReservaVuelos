package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aerolinea {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aerolinea_generador")
    @SequenceGenerator(name = "aerolinea_generador", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL)
    private List<Vuelo> vuelos;

    public Aerolinea(String nombreAerolinea) {
    }

    public Aerolinea() {

    }

    public void setId(String ap) {

    }
    }




