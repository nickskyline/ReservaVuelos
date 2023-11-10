package com.proyecto.reservavuelos.dto;
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
public class AerolineaDto {
    protected String nuevaYork;
    @NotNull
    private String nombre;

    @NotNull
    private String pais;
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    @NotNull
    private String telefono;

    public AerolineaDto(String aerolíneaNueva, String francia, String number) {
    }
}




