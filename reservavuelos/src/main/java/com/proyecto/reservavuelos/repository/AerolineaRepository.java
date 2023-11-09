package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AerolineaRepository extends JpaRepository<Aerolinea, Long> {
    Object findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    Optional<Aerolinea> findById(String id);
}
