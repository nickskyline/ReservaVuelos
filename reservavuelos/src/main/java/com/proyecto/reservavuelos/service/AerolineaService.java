package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.AerolineaDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaService {
    private final AerolineaRepository aerolineaRepository;
    private String nombreAerolinea;
    private String nuevaYork;

    @Autowired
    public AerolineaService(AerolineaRepository aerolineaRepository, String nuevaYork) {

        this.aerolineaRepository = aerolineaRepository;
        this.nuevaYork = nuevaYork;
    }

    public Aerolinea crearAerolinea(AerolineaDto aerolineaDto) {
        // Verificar si el nombre de la aerolínea es válido
        String nombre = aerolineaDto.getNombre();
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre de la aerolínea no es válido");
        }

        // Verificar si la aerolínea ya existe en la base de datos
        if (aerolineaRepository.existsByNombre(nombre)) {
            throw new IllegalArgumentException("La aerolínea ya existe");
        }


        // Crear una nueva instancia de Aerolinea
        Aerolinea aerolinea;
        aerolinea = new Aerolinea( );
        aerolinea.setNombre(nombre);
        aerolinea.setPais(aerolineaDto.getPais());
        aerolinea.setTelefono(aerolineaDto.getTelefono());

        // Guardar la aerolínea en la base de datos
        return aerolineaRepository.save(aerolinea);
    }


    public List<Aerolinea> obtenerTodasLasAerolineas() {
        return aerolineaRepository.findAll();
    }


    public Aerolinea obtenerAerolineaPorId(Long id) {
        Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);

        // Retorna null si la aerolínea no se encuentra
        return aerolineaOptional.orElse (null);
    }
    public List<Aerolinea> obtenerAerolineasPorCiudad(String ciudad) {
        // Lógica para obtener aerolíneas por ciudad desde el repositorio
        List<Aerolinea> aerolineas = (List<Aerolinea>) aerolineaRepository.findByCiudad(ciudad);

        if (aerolineas == null) {
            return Collections.emptyList(); // Devuelve una lista vacía si no hay aerolíneas
        }

        return aerolineas;
    }
    public List<Aerolinea> obtenerAerolineasPorPaisYCiudad(String pais, String ciudad) {
        // Verifica que los parámetros no sean nulos o vacíos
        if (pais == null || ciudad == null || pais.isEmpty() || ciudad.isEmpty()) {
            throw new IllegalArgumentException("Los parámetros 'pais' y 'ciudad' no pueden ser nulos ni vacíos.");
        }

        // Lógica para obtener aerolíneas por país y ciudad desde el repositorio
        List<Aerolinea> aerolineas = aerolineaRepository.findByPaisAndCiudad(pais, ciudad);

        // Puedes agregar más lógica según sea necesario

        return aerolineas;
    }


    public boolean actualizarAerolinea(Long id, AerolineaDto nuevaAerolinea) {
        Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);

        if (aerolineaOptional.isPresent()) {
            // Actualiza los atributos de la aerolínea existente con los valores de la nueva aerolínea
            Aerolinea aerolineaExistente = aerolineaOptional.get();
            aerolineaExistente.setNombre(nuevaAerolinea.getNombre());
            aerolineaExistente.setPais(nuevaAerolinea.getPais());

            // Guarda la aerolínea actualizada en la base de datos
            aerolineaRepository.save(aerolineaExistente);
            return true; // Aerolínea actualizada con éxito
        }

        return false; // Aerolínea no encontrada
    }

    public boolean eliminarAerolinea(Long id) {
        if (id != null) {
            Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);

            if (aerolineaOptional.isPresent()) {
                aerolineaRepository.deleteById(id);
                return true; // Aerolínea eliminada con éxito
            }
        }

        return false; // Aerolínea no encontrada o ID nulo
    }
    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }
}