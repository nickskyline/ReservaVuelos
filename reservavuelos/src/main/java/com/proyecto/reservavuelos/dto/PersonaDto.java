package com.proyecto.reservavuelos.dto;

import com.proyecto.reservavuelos.util.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

    @NotNull
    private String nombres;

    @NotNull
    private String apellidos;

    @NotNull
    private String genero;

    @NotNull
    private TipoDocumento tipoDocumento;

    @NotNull
    private String numeroDocumento;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private String paisOrigen;

    @NotNull
    private String paisResidencia;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String telefono;

}
