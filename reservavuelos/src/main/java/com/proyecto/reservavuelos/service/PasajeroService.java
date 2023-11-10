package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    public PasajeroService(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
    }
    public PasajeroService() {
    }
    public Pasajero crearPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajero = convertirDtoAPasajero(pasajeroDto);
        return pasajeroRepository.save(pasajero);
    }

    public Pasajero obtenerPasajeroPorId(Long id) {
        return pasajeroRepository.findById(id).orElse(null);
    }
    public List<Pasajero> obtenerTodosLosPasajeros() {
        List<Pasajero> pasajeros = pasajeroRepository.findAll();
        return pasajeros.stream()
                .map(this::convertirPasajeroADto)
                .collect(Collectors.toList());
    }

    public PasajeroDto actualizarPasajero(Long id, PasajeroDto pasajeroDto) {
        Pasajero pasajeroExistente = pasajeroRepository.findById(id).orElse(null);
        if (pasajeroExistente != null) {
            actualizarPasajeroDesdeDto(pasajeroExistente, pasajeroDto);
            pasajeroExistente.setId(id); // Aseguramos que el ID no cambie
            return convertirPasajeroADto(pasajeroRepository.save(pasajeroExistente));
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

    private Pasajero convertirDtoAPasajero(PasajeroDto pasajeroDto) {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(pasajeroDto.getNombre());
        pasajero.setApellido(pasajeroDto.getApellido());
        // Setear otros campos de Pasajero desde el DTO
        return pasajero;
    }

    private PasajeroDto convertirPasajeroADto(Pasajero pasajero) {
        PasajeroDto pasajeroDto = new PasajeroDto();
        pasajeroDto.setNombre(pasajero.getNombre());
        pasajeroDto.setApellido(pasajero.getApellido());
        // Setear otros campos de PasajeroDto desde el Pasajero
        return pasajeroDto;
    }

    private void actualizarPasajeroDesdeDto(Pasajero pasajero, PasajeroDto pasajeroDto) {
        pasajero.setNombre(pasajeroDto.getNombre());
        pasajero.setApellido(pasajeroDto.getApellido());
        // Actualizar otros campos de Pasajero desde el DTO
    }

    }

