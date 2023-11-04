package com.proyecto.reservavuelos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "pais", nullable = false)
    private String pais;
}
