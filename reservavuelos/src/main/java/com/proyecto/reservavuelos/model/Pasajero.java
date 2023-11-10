package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pasajero {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pasajero_generador")
    @SequenceGenerator(name = "pasajero_generador", allocationSize = 1)
    private Long id;

    @Column(name = "tipo_pasajero", nullable = false)
    private String tipoPasajero;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @ManyToMany(mappedBy = "pasajeros")
    private List<Reserva> reservas;

    public Pasajero(String tipoPasajero, Persona persona) {
        this.tipoPasajero = tipoPasajero;
        this.persona = persona;
    }

    public Pasajero() {
        // Constructor vacío requerido por JPA
    }

    public Pasajero(String nombre, String apellido, int i) {
    }

    public void setNombre(String nombre) {
    }

    public void setApellido(String apellido) {
    }

    public void setEdad(int edad) {
    }

    public String getNombre() {
        return null;
    }

    public String getApellido() {
        return null;
    }

    public int getEdad() {
        return 0;
    }
}