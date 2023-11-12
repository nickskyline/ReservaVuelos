package com.proyecto.reservavuelos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Reserva;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Pasajero extends Persona {
    public Pasajero(Long id, String tipoPasajero, Persona persona, List<Reserva> reservas) {
        this.id = id;
        this.tipoPasajero = tipoPasajero;
        this.persona = persona;
        this.reservas = reservas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_pasajero", nullable = false)
    private String tipoPasajero;

    @OneToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
    @OneToMany(mappedBy = "pasajeros")
    @JsonIgnore
    private List<Reserva> reservas;
   private String ValorEjemplo;
    public Pasajero() {

    }

 

    public String getCampoEjemplo() {

        return ValorEjemplo;
    }

    public void setCampoEjemplo(String valorEjemplo) {
    }
}