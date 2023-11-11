package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {
    boolean existsByNombreAndApellido(String nombre, String apellido);

    Object existsByNombreAndApellido(String nombre);
}
