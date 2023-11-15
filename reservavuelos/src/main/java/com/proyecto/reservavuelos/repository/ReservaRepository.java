package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.*;
import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Reserva r SET r.fechaReserva = :fechaReserva, r.pasajeros = :pasajeros, r.vuelo = :vuelo WHERE r.id = :reservaId")
    void actualizarVuelo(@Param("reservaId") Long reservaId,
                         @Param("fechaReserva") LocalDate fechaReserva,
                         @Param("pasajeros") Pasajero pasajeros,
                         @Param("vuelo")Vuelo vuelo);

}
