package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
}
