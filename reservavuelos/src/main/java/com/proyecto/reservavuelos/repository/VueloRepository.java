package com.proyecto.reservavuelos.repository;

import com.proyecto.reservavuelos.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {

    @Modifying
    @Query()

}
