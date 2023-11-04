package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.VueloDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import com.proyecto.reservavuelos.repository.CiudadRepository;
import com.proyecto.reservavuelos.repository.VueloRepository;
import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class VueloService {

    private VueloRepository vueloRepository;
    private CiudadRepository ciudadRepository;
    private AerolineaRepository aerolineaRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository, CiudadRepository ciudadRepository, AerolineaRepository aerolineaRepository) {
        this.vueloRepository = vueloRepository;
        this.ciudadRepository = ciudadRepository;
        this.aerolineaRepository = aerolineaRepository;
    }

    public Vuelo crearVuelo(VueloDto vueloDto) {

        // Validar la informacion proporcionada
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<VueloDto>> violations = validator.validate(vueloDto);

        if (!violations.isEmpty()) {
            throw new ValidationException("Errores de validación en los campos de la creacion del vuelo");
        }

        // Hallar las ciudades y aerolineas a traves del repositorio.
        Optional<Ciudad> ciudadOrigen = ciudadRepository.findById(vueloDto.getIdCiudadOrigen());
        Optional<Ciudad> ciudadDestino = ciudadRepository.findById(vueloDto.getIdCiudadDestino());
        Optional<Aerolinea> aerolinea = aerolineaRepository.findById(vueloDto.getIdAerolinea());

        if (ciudadOrigen.isPresent() && ciudadDestino.isPresent() && aerolinea.isPresent()) {

            // Formatear las fechas
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaSalidaConvertida = LocalDateTime.parse(vueloDto.getFechaSalida(), formatter);
            LocalDateTime fechaLlegadaConvertida = LocalDateTime.parse(vueloDto.getFechaLlegada(), formatter);

            if (fechaSalidaConvertida.isBefore(LocalDateTime.now()) || fechaSalidaConvertida.isBefore(LocalDateTime.parse("1900-01-01 10:10", formatter)) ||
                    fechaLlegadaConvertida.isBefore(LocalDateTime.parse("1900-01-01 10:10", formatter)) || fechaLlegadaConvertida.isBefore(fechaSalidaConvertida)) {
                throw new IllegalArgumentException("Las fechas de llegada y salida no pueden ser en el futuro, ni en el pasado!");
            }

            // Crear el vuelo
            Vuelo nuevoVuelo = new Vuelo(ciudadOrigen.get(), ciudadDestino.get(), fechaSalidaConvertida,
                    fechaLlegadaConvertida, vueloDto.getAsientosDisponibles(), vueloDto.getPrecio(),
                    vueloDto.getTipoVuelo(), aerolinea.get());

            // Enviarlo al repositorio
            return vueloRepository.save(nuevoVuelo);
        } else {
            throw new NoSuchElementException();
        }
    }

    public Vuelo obtenerVueloPorId(Long id) {
        return vueloRepository.findById(id).orElse(null);
    }

    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepository.findAll();
    }

    public Vuelo actualizarVuelo(Long id, Vuelo vueloActualizado) {
        Vuelo vueloExistente = vueloRepository.findById(id).orElse(null);
        if (vueloExistente != null) {
            vueloActualizado.setId(id); // Aseguramos que el ID no cambie
            return vueloRepository.save(vueloActualizado);
        }
        return null; // El vuelo no existe
    }

    public boolean eliminarVuelo(Long id) {
        Vuelo vuelo = vueloRepository.findById(id).orElse(null);
        if (vuelo != null) {
            vueloRepository.delete(vuelo);
            return true;
        }
        return false; // No se pudo eliminar el vuelo
    }
}
