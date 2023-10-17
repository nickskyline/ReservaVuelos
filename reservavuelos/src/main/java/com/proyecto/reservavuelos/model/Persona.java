package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
    @SequenceGenerator(name = "person_generator", allocationSize = 1)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "genero", nullable = false)
    private String genero;
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;
    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;
    @Column(name = "pais_residencia", nullable = false)
    private String paisResidencia;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToOne(mappedBy = "persona")
    private Pasajero pasajero;
}
