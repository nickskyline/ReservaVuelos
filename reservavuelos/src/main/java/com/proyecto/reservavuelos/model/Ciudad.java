package com.proyecto.reservavuelos.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ciudad_generador")
    @SequenceGenerator(name = "ciudad_generador", allocationSize = 1)
    public Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "pais", nullable = false)
    private String pais;
}
