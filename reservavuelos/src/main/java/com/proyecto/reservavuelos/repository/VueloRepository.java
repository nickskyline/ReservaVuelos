package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

        @Modifying
        @Transactional
        @Query("UPDATE Vuelo v SET v.ciudadOrigen = :ciudadOrigen, v.ciudadDestino = :ciudadDestino, " +
                "v.fechaSalida = :fechaSalida, v.fechaLlegada = :fechaLlegada, " +
                "v.asientosDisponibles = :asientosDisponibles, v.precio = :precio, " +
                "v.tipoVuelo = :tipoVuelo, v.aerolinea = :aerolinea " +
                "WHERE v.id = :vueloId")
        void actualizarVuelo(@Param("vueloId") Long vueloId,
                             @Param("ciudadOrigen") Ciudad ciudadOrigen,
                             @Param("ciudadDestino") Ciudad ciudadDestino,
                             @Param("fechaSalida") LocalDateTime fechaSalida,
                             @Param("fechaLlegada") LocalDateTime fechaLlegada,
                             @Param("asientosDisponibles") int asientosDisponibles,
                             @Param("precio") double precio,
                             @Param("tipoVuelo") TipoVuelo tipoVuelo,
                             @Param("aerolinea") Aerolinea aerolinea);

}

