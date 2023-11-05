package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CiudadService {
    private CiudadRepository ciudadRepository;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Ciudad agregarCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    public List<Ciudad> listarCiudades() {
        return ciudadRepository.findAll();
    }

    public boolean eliminarCiudadPorId(Long id) {
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(id);

        if (ciudadOptional.isPresent()) {
            ciudadRepository.delete(ciudadOptional.get());
            return true; // Ciudad eliminada con éxito
        }

        return false; // Ciudad no encontrada
    }
    public boolean actualizarCiudadPorId(Long id, Ciudad nuevaCiudad) {
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(id);

        if (ciudadOptional.isPresent()) {
            Ciudad ciudadExistente = ciudadOptional.get();

            // Actualiza los atributos de la ciudad existente con los valores de la nueva ciudad
            ciudadExistente.setNombre(nuevaCiudad.getNombre());
            ciudadExistente.setPais(nuevaCiudad.getPais());

            ciudadRepository.save(ciudadExistente);
            return true; // Ciudad actualizada con éxito
        }

        return false; // Ciudad no encontrada
    }
}
