package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaService {
    private AerolineaRepository aerolineaRepository;
    private String nombreAerolinea;
    private String codigoIATA;

    @Autowired
    public AerolineaService(AerolineaRepository aerolineaRepository) {

        this.aerolineaRepository = aerolineaRepository;
    }
    public Aerolinea crearAerolinea(Aerolinea aerolinea) {
        if (aerolinea.getNombre() == null || aerolinea.getNombre().isEmpty()) {
            // Realizar alguna acción o lanzar una excepción, pero no llamar a save
            throw new IllegalArgumentException("El nombre de la aerolínea no es válido");
        }
        return aerolineaRepository.save(aerolinea);
    }

    public List<Aerolinea> obtenerTodasLasAerolineas() {
        return aerolineaRepository.findAll();

    }

    public Aerolinea obtenerAerolineaPorId(Long id) {
        Optional<Aerolinea> aerolineaOptional = aerolineaRepository.findById(id);

        if (aerolineaOptional.isPresent()) {
            return aerolineaOptional.get();
        } else {
            return null; // Retorna null si la aerolínea no se encuentra
        }
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
        return null;
    }
}