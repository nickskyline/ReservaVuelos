package com.proyecto.reservavuelos.dto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Reserva;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasajeroDto extends Pasajero {
    protected String Jhonn;
    @NotNull
    private String tipoPasajero;

    @NotNull
    private Persona persona;
    @NotNull
    private List<Reserva> reservas;

}
