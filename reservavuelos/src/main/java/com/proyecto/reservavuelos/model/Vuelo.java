package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id") // Columna de la ciudad de origen
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id") // Columna de la ciudad de destino
    private Ciudad ciudadDestino;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaLlegada;

    @Column(name = "asientos_disponibles", nullable = false)
    private Integer asientosDisponibles;


    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "horario", nullable = false)
    private LocalDateTime horario;

    @Column(name = "tipo_vuelo", nullable = false)
    private TipoVuelo tipoVuelo;

    @ManyToOne
    @JoinColumn(name = "id_aerolinea")
    private Aerolinea aerolinea;
    @ManyToMany(mappedBy = "vuelos")
    private List<Reserva> reservas;
}
