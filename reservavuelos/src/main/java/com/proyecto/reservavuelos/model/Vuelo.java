package com.proyecto.reservavuelos.model;

import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_origen_id", referencedColumnName = "id") // Columna de la ciudad de origen
    @NotNull
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "ciudad_destino_id", referencedColumnName = "id") // Columna de la ciudad de destino
    @NotNull
    private Ciudad ciudadDestino;

    @Column(name = "fecha_salida", nullable = false)
    @NotNull
    private LocalDateTime fechaSalida;

    @Column(name = "fecha_llegada", nullable = false)
    @NotNull
    private LocalDateTime fechaLlegada;

    @Column(name = "asientos_disponibles", nullable = false)
    @NotNull
    private Integer asientosDisponibles;


    @Column(name = "precio", nullable = false)
    @NotNull
    private Double precio;

    @Column(name = "tipo_vuelo", nullable = false)
    @NotNull
    private TipoVuelo tipoVuelo;

    @ManyToOne
    @JoinColumn(name = "id_aerolinea", referencedColumnName = "id")
    private Aerolinea aerolinea;
    @ManyToMany(mappedBy = "vuelos")
    private List<Reserva> reservas;

    public Vuelo(Ciudad ciudadOrigen, Ciudad ciudadDestino, LocalDateTime fechaSalida, LocalDateTime fechaLlegada, Integer asientosDisponibles, Double precio, TipoVuelo tipoVuelo, Aerolinea aerolinea) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.asientosDisponibles = asientosDisponibles;
        this.precio = precio;
        this.tipoVuelo = tipoVuelo;
        this.aerolinea = aerolinea;
    }
}
