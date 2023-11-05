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
        return reservaRepository.save(reserva);
    }

    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public List<Reserva> obtenerTodasLasReservas() {
        return reservaRepository.findAll();
    }

    public Reserva actualizarReserva(Long id, Reserva reservaActualizada) {
        Reserva reservaExistente = reservaRepository.findById(id).orElse(null);
        if (reservaExistente != null) {
            reservaActualizada.setId(id); // Aseguramos que el ID no cambie


            return reservaRepository.save(reservaActualizada);
        }
        return null; // La reserva no existe
    }

    public boolean eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            reservaRepository.delete(reserva);
            return true;
        }
        return false; // No se pudo eliminar la reserva
    }
}
