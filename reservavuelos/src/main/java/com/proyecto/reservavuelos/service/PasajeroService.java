package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.dto.PasajeroDto;
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

    public PasajeroDto obtenerPasajeroPorId(Long id) throws PasajeroServiceException {
        try {
            // Buscar el Pasajero en el repositorio
            Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);

            if (pasajeroOptional.isPresent()) {
                Pasajero pasajero = pasajeroOptional.get();

                // Crear un PasajeroDto y asignar los campos
                PasajeroDto pasajeroDto = new PasajeroDto();
                pasajeroDto.setId(pasajero.getId());
                pasajeroDto.setNombre(pasajero.getNombre()); // Reemplaza con los campos adecuados
                pasajeroDto.setApellido(pasajero.getApellido());
                pasajeroDto.setEdad(pasajero.getEdad());
                // Agregar más campos según sea necesario

                return pasajeroDto;
            } else {
                // Manejar el caso en que el Pasajero no se encuentre
                throw new PasajeroNotFoundException("No se encontró un Pasajero con el ID: " + id);
            }
        } catch (Exception ex) {
            // Manejar cualquier excepción no controlada, por ejemplo, problemas en el repositorio
            throw new PasajeroServiceException("Error al obtener el Pasajero por ID: " + id, ex);
        } catch (PasajeroNotFoundException e) {
            throw new RuntimeException (e);
        }
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
