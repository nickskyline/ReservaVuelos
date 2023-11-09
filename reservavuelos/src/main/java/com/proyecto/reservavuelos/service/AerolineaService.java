package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.AerolineaDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaService {
    private final AerolineaRepository aerolineaRepository;
    private String nombreAerolinea;
    private String nuevaYork;

    @Autowired
    public AerolineaService(AerolineaRepository aerolineaRepository) {

        this.aerolineaRepository = aerolineaRepository;
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
        aerolinea = new Aerolinea("Delta Airlines");
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

    public boolean actualizarAerolinea(Long id, Aerolinea nuevaAerolinea) {
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
        Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);

        if (aerolineaOptional.isPresent()) {
            aerolineaRepository.delete(aerolineaOptional.get());
            return true; // Aerolínea eliminada con éxito
        }

        return false; // Aerolínea no encontrada
    }


    public List<Aerolinea> obtenerAerolineasPorCiudad(String estadosUnidos) {
        return null;
    }

    public List<Aerolinea> obtenerAerolineasPorPaisYCiudad(String estadosUnidos, String nuevaYork) {
        this.nuevaYork = nuevaYork;
        return null;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }
}