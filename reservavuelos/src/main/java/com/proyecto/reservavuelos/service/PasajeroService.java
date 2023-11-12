package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {

    private PasajeroRepository pasajeroRepository;

    @Autowired
    public PasajeroService() {
        this.pasajeroRepository = pasajeroRepository;
    }

    public Pasajero crearPasajero(Pasajero pasajeroDto) {
        // Realizar validaciones en pasajeroDto si es necesario
        Pasajero pasajero = convertirPasajeroDtoAEntidad(pasajeroDto);
        return pasajeroRepository.save(pasajero);


    }
    Pasajero convertirPasajeroDtoAEntidad(Pasajero pasajeroDto) {
        return pasajeroDto;
    }
    private Pasajero convertirPasajeroDtoAEntidad(PasajeroDto pasajeroDto) {
        Pasajero pasajero = new Pasajero();

        // Copiar los campos específicos de Pasajero desde PasajeroDto
        pasajero.setTipoPasajero(pasajeroDto.getTipoPasajero());
        pasajero.setReservas(pasajeroDto.getReservas());

        // Copiar la instancia de Persona desde PasajeroDto
        pasajero.setPersona(pasajeroDto.getPersona());

        // Puedes realizar otras configuraciones específicas de Pasajero si es necesario

        return pasajero;
    }

    public Pasajero actualizarPasajero(Long id, Pasajero pasajeroDto) {
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);

        if (pasajeroOptional.isPresent()) {
            Pasajero pasajeroExistente = pasajeroOptional.get();
            // Realizar validaciones en pasajeroDto si es necesario
            actualizarDatosPasajeroDesdeDto(pasajeroExistente, pasajeroDto);
            return pasajeroRepository.save(pasajeroExistente);
        }

        return null; // El pasajero no existe
    }

    /*private void actualizarDatosPasajeroDesdeDto(Pasajero pasajeroExistente, PasajeroDto pasajeroDto) {
        pasajeroExistente.setPasajero (pasajeroDto.getPersona ( ).getPasajero ());
        pasajeroExistente.setTipoPasajero (pasajeroDto.getTipoPasajero ());

    }*/
    void actualizarDatosPasajeroDesdeDto(Pasajero pasajeroExistente, Pasajero pasajeroDto) {
        // Actualizar campos específicos de Pasajero
        pasajeroExistente.setTipoPasajero(pasajeroDto.getTipoPasajero());

        // Actualizar la instancia de Persona en Pasajero
        Persona personaExistente = pasajeroExistente.getPersona();
        Persona personaDesdeDto = pasajeroDto.getPersona();

        if (personaExistente != null && personaDesdeDto != null) {
            // Actualizar campos heredados de Persona
            personaExistente.setPasajero(personaDesdeDto.getPasajero());
        }
    }
    public Pasajero obtenerPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }

    public List<Pasajero> obtenerTodosLosPasajeros() {
        return pasajeroRepository.findAll();
    }

    public boolean eliminarPasajero(Long id) {
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);

        if (pasajeroOptional.isPresent()) {
            pasajeroRepository.delete(pasajeroOptional.get());
            return true;
        }

        return false; // No se pudo eliminar el pasajero
    }
}

