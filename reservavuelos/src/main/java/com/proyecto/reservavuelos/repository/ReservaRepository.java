package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
