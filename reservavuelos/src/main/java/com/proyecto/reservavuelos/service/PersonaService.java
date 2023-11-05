package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.PersonaDto;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.repository.PersonaRepository;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonaService {

    private PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona crearPersona(PersonaDto personaDto) {

        // Validar informacion
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonaDto>> violations = validator.validate(personaDto);

        if (!violations.isEmpty()) {
            throw new ValidationException("Errores de validación en los campos de persona");
        }

        // Formatear las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimientoConvertida = LocalDate.parse(personaDto.getFechaNacimiento(), formatter);

        if (fechaNacimientoConvertida.isAfter(LocalDate.now()) || fechaNacimientoConvertida.isBefore(LocalDate.parse("1900-01-01"))) {
            throw new IllegalArgumentException("Su fecha de nacimiento no puede ser en el futuro, ni en el pasado!");
        }

            //Creacion de la persona
            Persona nuevaPersona = new Persona(personaDto.getNombres(), personaDto.getApellidos(), personaDto.getGenero(),
                    personaDto.getTipoDocumento(), personaDto.getNumeroDocumento(), fechaNacimientoConvertida,
                    personaDto.getPaisOrigen(), personaDto.getPaisResidencia(), personaDto.getEmail(), personaDto.getTelefono());

            return personaRepository.save(nuevaPersona);

    }

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    public Persona buscarPorNumeroDocumento(String numeroDocumento) {
        // Utiliza el método findByNumeroDocumento del repositorio para buscar una persona por su número de documento
        return personaRepository.findByNumeroDocumento(numeroDocumento);
    }

    public boolean actualizarPersona(Long id, PersonaDto personaDto) {

        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            Persona personaExistente = personaOptional.get();

            // Validar informacion
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<PersonaDto>> violations = validator.validate(personaDto);

            if (!violations.isEmpty()) {
                throw new ValidationException("Errores de validación en los campos de persona");
            }

            // Formatear las fechas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDate fechaNacimientoConvertida = LocalDate.parse(personaDto.getFechaNacimiento(), formatter);

            //Creacion de la persona
             personaExistente = new Persona(personaDto.getNombres(), personaDto.getApellidos(), personaDto.getGenero(),
                    personaDto.getTipoDocumento(), personaDto.getNumeroDocumento(), fechaNacimientoConvertida,
                    personaDto.getPaisOrigen(), personaDto.getPaisResidencia(), personaDto.getEmail(), personaDto.getTelefono());

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
