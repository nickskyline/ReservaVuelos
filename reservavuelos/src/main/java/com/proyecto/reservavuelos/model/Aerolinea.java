package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Aerolinea(Long id, String nombre, String pais, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.telefono = telefono;
    }
}
