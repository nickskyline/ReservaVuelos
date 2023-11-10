package com.proyecto.reservavuelos.model;

import com.proyecto.reservavuelos.dto.AerolineaDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aerolinea extends AerolineaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aerolinea_generador")
    @SequenceGenerator(name = "aerolinea_generador", allocationSize = 1)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL)
    private List<Vuelo> vuelos;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.pais = pais;
        this.telefono = telefono;
    }

    public Aerolinea() {

    }

    public Aerolinea(String deltaAirlines, String nuevaYork) {
    }

    public Aerolinea(String britishAirways, String reinoUnido, String londres) {

    }

    public Aerolinea(long l, String deltaAirlines, String pais, String ciudad) {

    }

    public void setId(String ap) {
    }
    public boolean getCiudad() {
        return false;
    }
}




