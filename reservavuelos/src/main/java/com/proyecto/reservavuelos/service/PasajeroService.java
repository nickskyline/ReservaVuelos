package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    public PasajeroService(PasajeroRepository pasajeroRepository) {
    }   public PasajeroService( ){
    }

    public Pasajero crearPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajero = convertirDtoAPasajero(pasajeroDto);
        return pasajeroRepository.save(pasajero);
    }

    public Pasajero obtenerPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    public List<Pasajero> obtenerTodosLosPasajeros() {
        return pasajeroRepository.findAll();
    }
    public ResponseEntity<Pasajero> actualizarPasajero(Long id, PasajeroDto pasajeroActualizado) {
        Pasajero pasajeroExistente = pasajeroRepository.findById(id).orElse(null);

        if (pasajeroExistente != null) {
            // Aseguramos que el ID no cambie
            pasajeroActualizado.setId(id);

            // Actualizamos los campos que sean necesarios
            pasajeroExistente.setTipoPasajero(pasajeroActualizado.getTipoPasajero());
            // Actualiza otros campos aquí según tus necesidades

            Pasajero pasajeroActualizadoEntity = pasajeroRepository.save(pasajeroExistente);
            return ResponseEntity.ok(pasajeroActualizadoEntity);
        } else {
            // Devuelve una respuesta 404 Not Found si el pasajero no existe
            return ResponseEntity.notFound().build();
        }
    }


    public boolean eliminarPasajero(Long id) {
        Pasajero pasajero = pasajeroRepository.findById(id).orElse(null);
        if (pasajero != null) {
            pasajeroRepository.delete(pasajero);
            return true;
        }
        return false; // No se pudo eliminar el pasajero
    }

    private Pasajero convertirDtoAPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajero = new Pasajero();
        pasajero.setTipoPasajero(pasajeroDto.getTipoPasajero());
        // Asigna otros campos según sea necesario
        return pasajero;
    }
}
