package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
