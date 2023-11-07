package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.model.Reserva;
import com.proyecto.reservavuelos.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }



    public Reserva crearReserva(Reserva reserva) {
        // Aquí puedes realizar validaciones antes de guardar la reserva si es necesario

        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser null");
        }

        return reservaRepository.save(reserva);
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva actualizarReserva(Long id, Reserva reservaActualizada) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        if (reservaActualizada == null) {
            throw new IllegalArgumentException("La reserva actualizada no puede ser null");
        }
        Reserva reservaExistente = reservaRepository.findById(id).orElse(null);
        if (reservaExistente != null) {
            reservaActualizada.setId(id); // Aseguramos que el ID no cambie
            // Aquí puedes realizar otras actualizaciones necesarias
            return reservaRepository.save(reservaActualizada);
        }
        return null; // La reserva no existe
    }


    public boolean eliminarReserva(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser null");
        }
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            reservaRepository.delete(reserva);
            return true;
        }
        return false; // No se pudo eliminar la reserva
    }
}
