package com.proyecto.reservavuelos.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vuelo_generador")
    @SequenceGenerator(name = "vuelo_generador", allocationSize = 1)
    public Long id;

    @Column(name = "ciudad_origen")
    private Ciudad ciudadOrigen;

    @Column(name = "ciudad_destino")
    private Ciudad ciudadDestino;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "fecha_entrada")
    private LocalDate fechaLlegada;

    @Column(name = "asientos_disponibles")
    private Integer asientosDisponibles;


    @Column(name = "id_aerolinea")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Long id_aerolinea;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "horario")
    private LocalDateTime horario;

    @Column(name = "tipo_vuelo")
    private TipoVuelo tipoVuelo;

}
