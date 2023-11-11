package com.proyecto.reservavuelos.dto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Reserva;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PasajeroDto extends Pasajero {
    @NotNull
     private Long id  ;
    @NotNull
    private String tipoPasajero;
    @NotNull
    private  List<Reserva> reservas;

    @NotNull
    private Persona persona;

    public void setDocumento(String number) {
    }

    public void setEdad(int i) {
    }

    public void setNombre(String johnDoe) {
    }
}