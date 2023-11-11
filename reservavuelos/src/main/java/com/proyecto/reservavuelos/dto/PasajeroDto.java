package com.proyecto.reservavuelos.dto;

import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Reserva;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PasajeroDto {
    @NotNull
    private String tipoPasajero;

    @NotNull
    private Persona persona;

    private List<Reserva> reservas;

    public void setId(Long id) {
    }
}
