package com.proyecto.reservavuelos.dto;

import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.validation.constraints.Min;
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
public class VueloDto {

    @NotNull
    private Long idCiudadOrigen;

    @NotNull
    private Long idCiudadDestino;

    @NotNull
    private String fechaSalida;

    @NotNull
    private String fechaLlegada;

    @NotNull
    @Min(value=0)
    private Integer asientosDisponibles;

    @NotNull
    @Min(value=0)
    private Double precio;

    @NotNull
    private TipoVuelo tipoVuelo;

    @NotNull
    private Long idAerolinea;

}
