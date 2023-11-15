package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.CiudadDto;
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
        if (ciudad == null || ciudad.getNombre() == null || ciudad.getPais() == null) {
            throw new IllegalArgumentException("La ciudad, el nombre y el país no pueden ser null");
        }
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

        if (id == null || nuevaCiudad == null || nuevaCiudad.getNombre() == null || nuevaCiudad.getPais() == null) {
            throw new IllegalArgumentException("El ID, la ciudad, el nombre y el país no pueden ser null");
        }
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

    /*public void actualizarVuelo(Long vueloId, VueloDto vueloDto) {
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
    }*/
}
