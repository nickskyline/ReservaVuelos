package com.proyecto.reservavuelos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private String fechaReserva;
    private Long idPasajero;
    private Long idVuelo;
}
