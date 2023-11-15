package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.ReservaDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.model.Reserva;
import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.repository.PersonaRepository;
import com.proyecto.reservavuelos.repository.ReservaRepository;
import com.proyecto.reservavuelos.repository.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private PasajeroRepository pasajeroRepository;
    private VueloRepository vueloRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, PasajeroRepository pasajeroRepository, VueloRepository vueloRepository) {
        this.reservaRepository = reservaRepository;
        this.pasajeroRepository = pasajeroRepository;
        this.vueloRepository = vueloRepository;
    }



    public Reserva crearReserva(ReservaDto reservaDto) {
        if (reservaDto == null) {
            throw new IllegalArgumentException("La reserva no puede ser null");
        }

        Optional<Pasajero> pasajero = pasajeroRepository.findById(reservaDto.getIdPasajero());
        Optional<Vuelo> vuelo = vueloRepository.findById(reservaDto.getIdVuelo());

        if(!pasajero.isPresent()){
            throw new NoSuchElementException("No se encontró un pasajero con el ID proporcionado");
        }
        if(!vuelo.isPresent()){
            throw new NoSuchElementException("No se encontró un vuelo con el ID proporcionado");
        }

        LocalDate fechaReservaConvertida = convertirYValidarFechas(reservaDto);
        Reserva reserva = new Reserva(fechaReservaConvertida, pasajero.get(), vuelo.get());
        return reservaRepository.save(reserva);
    }

    private LocalDate convertirYValidarFechas(ReservaDto reservaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaReserva = LocalDate.parse(reservaDto.getFechaReserva(), formatter);


        LocalDate fechaMinima = LocalDate.parse("1900-01-01", formatter);
        LocalDate fechaActual = LocalDate.now();

        if (fechaReserva.isBefore(fechaActual)) {
            throw new IllegalArgumentException("La fecha de reserva no puede ser antes de hoy!");
        }
        return fechaReserva;
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
