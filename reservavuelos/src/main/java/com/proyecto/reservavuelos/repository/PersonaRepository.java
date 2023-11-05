package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByNumeroDocumento(String numeroDocumento);
}
