
package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.model.Reserva;

import com.proyecto.reservavuelos.repository.ReservaRepository;
import com.proyecto.reservavuelos.service.ReservaService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservaTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository mockReservaRepository;

    @BeforeEach
    public void setUp() {
        mockReservaRepository = Mockito.mock(ReservaRepository.class);
        reservaService = new ReservaService(mockReservaRepository);
    }

    @Test
    public void crearReservaTest() {
        // Arrange
        Reserva reserva = new Reserva();
        when(mockReservaRepository.save(any())).thenReturn(reserva);

        // Act
        Reserva nuevaReserva = reservaService.crearReserva(reserva);

        // Assert
        assertNotNull(nuevaReserva);
        verify(mockReservaRepository, times(1)).save(reserva);
    }

    @Test
    public void obtenerReservaPorIdTest() {
        // Arrange
        Long id = 1L;
        Reserva reserva = new Reserva();
        when(mockReservaRepository.findById(id)).thenReturn(Optional.of(reserva));

        // Act
        Reserva reservaObtenida = reservaService.obtenerReservaPorId(id);

        // Assert
        assertNotNull(reservaObtenida);
        assertEquals(reserva, reservaObtenida);
    }

    @Test
    public void obtenerReservaPorIdTest_Null() {
        // Arrange
        Long id = 1L;
        when(mockReservaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Reserva reservaObtenida = reservaService.obtenerReservaPorId(id);

        // Assert
        assertNull(reservaObtenida);
    }

    @Test
    public void actualizarReservaTestCuandoReservaNoExistente() {
        // Arrange
        Long id = 1L;
        Reserva reservaActualizada = new Reserva();
        when(mockReservaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Reserva reservaObtenida = reservaService.actualizarReserva(id, reservaActualizada);

        // Assert
        assertNull(reservaObtenida);
    }

    @Test
    public void eliminarReservaTestCuandoReservaNoExistente() {
        // Arrange
        Long id = 1L;
        when(mockReservaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = reservaService.eliminarReserva(id);

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void crearReservaTest_RepositoryThrowsException() {
        // Arrange
        Reserva reserva = new Reserva();
        when(mockReservaRepository.save(any())).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reservaService.crearReserva(reserva));
    }

    @Test
    public void obtenerTodasLasReservasTest() {
        // Arrange
        when(mockReservaRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Reserva> reservas = reservaService.obtenerTodasLasReservas();

        // Assert
        assertNotNull(reservas);
        assertTrue(reservas.isEmpty());
    }

    @Test
    public void obtenerTodasLasReservasTest_RepositoryThrowsException() {
        // Arrange
        when(mockReservaRepository.findAll()).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reservaService.obtenerTodasLasReservas());
    }

    @Test
    public void actualizarReservaTest_RepositoryThrowsException() {
        // Arrange
        Long id = 1L;
        Reserva reservaActualizada = new Reserva();
        when(mockReservaRepository.findById(id)).thenReturn(Optional.of(reservaActualizada));
        when(mockReservaRepository.save(any())).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reservaService.actualizarReserva(id, reservaActualizada));
    }

    @Test
    public void eliminarReservaTest_RepositoryThrowsException() {
        // Arrange
        Long id = 1L;
        Reserva reserva = new Reserva();
        when(mockReservaRepository.findById(id)).thenReturn(Optional.of(reserva));
        doThrow(new RuntimeException()).when(mockReservaRepository).delete(any());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reservaService.eliminarReserva(id));
    }

    @Test
    public void crearReservaTestConReservaNull() {
        // Arrange
        Reserva reserva = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.crearReserva(reserva));
    }

    @Test
    public void actualizarReservaTest_ReservaActualizadaNull() {
        // Arrange
        Long id = 1L;
        Reserva reservaActualizada = null;
        when(mockReservaRepository.findById(id)).thenReturn(Optional.of(new Reserva()));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.actualizarReserva(id, reservaActualizada));
    }

    @Test
    public void actualizarReservaTestConIdNull() {
        // Arrange
        Long id = null;
        Reserva reservaActualizada = new Reserva();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.actualizarReserva(id, reservaActualizada));
    }

    @Test
    public void eliminarReservaTestConIdNull() {
        // Arrange
        Long id = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.eliminarReserva(id));
    }
}