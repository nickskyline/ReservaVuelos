package com.proyecto.reservavuelos.model;

import com.proyecto.reservavuelos.util.Genero;
import com.proyecto.reservavuelos.util.TipoDocumento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "genero", nullable = false)
    @NotNull
    private Genero genero;

    @Column(name = "tipo_documento", nullable = false)
    @NotNull
    private TipoDocumento tipoDocumento;

    @Column(name = "numero_documento", nullable = false)
    @NotNull
    private String numeroDocumento;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull
    private LocalDate fechaNacimiento;

    @Column(name = "pais_origen", nullable = false)
    @NotNull
    private String paisOrigen;

    @Column(name = "pais_residencia", nullable = false)
    @NotNull
    private String paisResidencia;

    @Column(name = "email", nullable = false)
    @NotNull
    @Email
    private String email;

    @Column(name = "telefono", nullable = false)
    @NotNull
    private String telefono;


    @OneToOne(mappedBy = "persona")
    private Pasajero pasajero;

    public Persona(String nombres, String apellidos, Genero genero, TipoDocumento tipoDocumento, String numeroDocumento, LocalDate fechaNacimiento, String paisOrigen, String paisResidencia, String email, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.paisOrigen = paisOrigen;
        this.paisResidencia = paisResidencia;
        this.email = email;
        this.telefono = telefono;
    }

}
