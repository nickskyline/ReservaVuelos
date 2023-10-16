package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }
    public Persona buscarPorNumeroDocumento(String numeroDocumento) {
        // Utiliza el método findByNumeroDocumento del repositorio para buscar una persona por su número de documento
        return personaRepository.findByNumeroDocumento(numeroDocumento);
    }

    public boolean actualizarPersona(Long id, Persona nuevaPersona) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            Persona personaExistente = personaOptional.get();
            personaExistente.setNombres(nuevaPersona.getNombres());
            personaExistente.setApellidos(nuevaPersona.getApellidos());
            personaExistente.setGenero(nuevaPersona.getGenero());
            personaExistente.setTipoDocumento(nuevaPersona.getTipoDocumento());
            personaExistente.setNumeroDocumento(nuevaPersona.getNumeroDocumento());
            personaExistente.setFechaNacimiento(nuevaPersona.getFechaNacimiento());
            personaExistente.setPaisOrigen(nuevaPersona.getPaisOrigen());
            personaExistente.setPaisResidencia(nuevaPersona.getPaisResidencia());
            personaExistente.setEmail(nuevaPersona.getEmail());
            personaExistente.setTelefono(nuevaPersona.getTelefono());

            // Guarda la persona actualizada en la base de datos
            personaRepository.save(personaExistente);
            return true; // Persona actualizada con éxito
        }

        return false; // Persona no encontrada
    }

    public boolean eliminarPersona(Long id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            personaRepository.delete(personaOptional.get());
            return true; // Persona eliminada con éxito
        }

        return false; // Persona no encontrada
    }
}
