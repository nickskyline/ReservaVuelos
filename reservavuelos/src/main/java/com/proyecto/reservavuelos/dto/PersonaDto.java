package com.proyecto.reservavuelos.dto;

import com.proyecto.reservavuelos.util.Genero;
import com.proyecto.reservavuelos.util.TipoDocumento;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
    private Genero genero;

    @NotNull
    private TipoDocumento tipoDocumento;

    @NotNull
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String numeroDocumento;

    @NotNull
    private String fechaNacimiento;

    @NotNull
    private String paisOrigen;

    @NotNull
    private String paisResidencia;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String telefono;

}
