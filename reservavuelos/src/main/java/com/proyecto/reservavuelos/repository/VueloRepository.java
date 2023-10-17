package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}
