package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloService {

    private VueloRepository vueloRepository;

    @Autowired
    public VueloService(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    public Vuelo crearVuelo(Vuelo vuelo) {

        if (vuelo.getCiudadOrigen() == null ||
            vuelo.getCiudadDestino() == null ||
            vuelo.getFechaSalida() == null ||
            vuelo.getFechaLlegada() == null ||
            vuelo.getAsientosDisponibles() == null ||
            vuelo.getPrecio() == null ||
            vuelo.getHorario() == null ||
            vuelo.getTipoVuelo() == null ||
            vuelo.getAerolinea() == null ) {

            throw new IllegalArgumentException();

        }

        return vueloRepository.save(vuelo);
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
