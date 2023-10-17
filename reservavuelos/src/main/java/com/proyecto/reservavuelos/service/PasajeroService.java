package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.model.Pasajero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    public Pasajero crearPasajero(Pasajero pasajero) {
        return pasajeroRepository.save(pasajero);
    }

    public Pasajero obtenerPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    public List<Pasajero> obtenerTodosLosPasajeros() {
        return pasajeroRepository.findAll();
    }

    public Pasajero actualizarPasajero(Long id, Pasajero pasajeroActualizado) {
        Pasajero pasajeroExistente = pasajeroRepository.findById(id).orElse(null);
        if (pasajeroExistente != null) {
            pasajeroActualizado.setId(id); // Aseguramos que el ID no cambie
            return pasajeroRepository.save(pasajeroActualizado);
        }
        return null; // El pasajero no existe
    }

    public boolean eliminarPasajero(Long id) {
        Pasajero pasajero = pasajeroRepository.findById(id).orElse(null);
        if (pasajero != null) {
            pasajeroRepository.delete(pasajero);
            return true;
        }
        return false; // No se pudo eliminar el pasajero
    }
}