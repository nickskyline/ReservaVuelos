package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona")
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

    @Column(name = "fecha_de_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;

    @Column(name = "pais_residencia", nullable = false)
    private String paisesResidencia;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "pasajero", cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona")
    private Pasajero pasajero;
}
