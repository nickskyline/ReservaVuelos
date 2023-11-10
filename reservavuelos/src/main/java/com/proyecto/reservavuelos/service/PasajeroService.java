package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.model.Pasajero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PasajeroService {
    @Autowired
    private  final PasajeroRepository pasajeroRepository;
    public PasajeroService(PasajeroRepository pasajeroRepository1) {
        this.pasajeroRepository = pasajeroRepository1;
    }
    public Pasajero crearPasajero(PasajeroDto pasajeroDto) {
        // Aquí deberías realizar la conversión de PasajeroDto a Pasajero
        Pasajero pasajero = convertirDtoAPasajero( );

        // Guarda el pasajero en el repositorio y devuelve el resultado
        assert pasajero != null;
        return pasajeroRepository.save(pasajero);
    }
    private Pasajero convertirDtoAPasajero() {
        return null;
    }
    public Pasajero obtenerPasajeroPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del pasajero no es válido");
        }

        return pasajeroRepository.findById(id).orElse(null);
    }
    public List<Pasajero> obtenerTodosLosPasajeros() {
        List<Pasajero> pasajeros = pasajeroRepository.findAll();

        if (pasajeros == null) {
            throw new RuntimeException("Error al obtener la lista de pasajeros");
        }

        return pasajeros;
    }


    public Pasajero actualizarPasajero(Long id, Pasajero pasajeroActualizado) {
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);

        if (((Optional<?>) pasajeroOptional).isPresent()) {
            Pasajero pasajeroExistente = pasajeroOptional.get();
            pasajeroActualizado.setId(pasajeroExistente.getId()); // Aseguramos que el ID no cambie
            return pasajeroRepository.save(pasajeroActualizado);
        } else {
            throw new IllegalArgumentException("Pasajero no encontrado con ID: " + id);
        }
    }

    public boolean eliminarPasajero(Long id) {
        Optional<Pasajero> pasajeroOptional = pasajeroRepository.findById(id);

        if (pasajeroOptional.isPresent()) {
            Pasajero pasajero = pasajeroOptional.get();
            pasajeroRepository.delete(pasajero);
            return true; // Pasajero eliminado con éxito
        }

        return false; // Pasajero no encontrado
    }

}