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


    private LocalDateTime convertirYValidarFechas(VueloDto vueloDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaSalidaConvertida = LocalDateTime.parse(vueloDto.getFechaSalida(), formatter);
        LocalDateTime fechaLlegadaConvertida = LocalDateTime.parse(vueloDto.getFechaLlegada(), formatter);

        LocalDateTime fechaMinima = LocalDateTime.parse("1900-01-01 10:10", formatter);
        LocalDateTime fechaActual = LocalDateTime.now();

        if (fechaSalidaConvertida.isBefore(fechaActual) || fechaSalidaConvertida.isBefore(fechaMinima) ||
                fechaLlegadaConvertida.isBefore(fechaMinima) || fechaLlegadaConvertida.isBefore(fechaSalidaConvertida)) {
            throw new IllegalArgumentException("Las fechas de llegada y salida no pueden ser en el futuro, ni en el pasado!");
        }

        return fechaSalidaConvertida;
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

            LocalDateTime fechaSalidaConvertida = convertirYValidarFechas(vueloDto);
            LocalDateTime fechaLlegadaConvertida = convertirYValidarFechas(vueloDto);

            // Crear el vuelo
            Vuelo nuevoVuelo = new Vuelo(ciudadOrigen.get(), ciudadDestino.get(), fechaSalidaConvertida,
                    fechaLlegadaConvertida, vueloDto.getAsientosDisponibles(), vueloDto.getPrecio(),
                    vueloDto.getTipoVuelo(), aerolinea.get());

            // Enviarlo al repositorio
            vueloRepository.save(nuevoVuelo);
            return nuevoVuelo;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Vuelo obtenerVueloPorId(Long id) {

        Optional<Vuelo> optionalVuelo = vueloRepository.findById(id);

        if (optionalVuelo.isPresent()) {
            return optionalVuelo.get();
        } else {
            return null;
        }
    }

    public List<Vuelo> obtenerTodosLosVuelos() {
        return vueloRepository.findAll();
    }

    public void actualizarVuelo(Long vueloId, VueloDto vueloDto) {
        // Realizar las validaciones necesarias en caso de ser necesario

        Ciudad ciudadOrigen = ciudadRepository.findById(vueloDto.getIdCiudadOrigen()).orElseThrow(NoSuchElementException::new);
        Ciudad ciudadDestino = ciudadRepository.findById(vueloDto.getIdCiudadDestino()).orElseThrow(NoSuchElementException::new);
        Aerolinea aerolinea = aerolineaRepository.findById(vueloDto.getIdAerolinea()).orElseThrow(NoSuchElementException::new);

        LocalDateTime fechaSalidaConvertida = convertirYValidarFechas(vueloDto);
        LocalDateTime fechaLlegadaConvertida = convertirYValidarFechas(vueloDto);

        vueloRepository.actualizarVuelo(vueloId, ciudadOrigen, ciudadDestino,
                fechaSalidaConvertida,
                fechaLlegadaConvertida,
                vueloDto.getAsientosDisponibles(),
                vueloDto.getPrecio(),
                vueloDto.getTipoVuelo(),
                aerolinea);
    }

    public boolean eliminarVuelo(Long id) {
        Optional<Vuelo> vueloOptional = vueloRepository.findById(id);

        if (vueloOptional.isPresent()) {
            vueloRepository.delete(vueloOptional.get());
            return true; // Persona eliminada con éxito
        }
        return false; // Persona no encontrada
    }
}
