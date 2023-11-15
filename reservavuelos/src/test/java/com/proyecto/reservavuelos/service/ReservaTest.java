
package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.ReservaDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.model.Reserva;

import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.repository.ReservaRepository;
import com.proyecto.reservavuelos.repository.VueloRepository;
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
    @Mock
    private PasajeroRepository mockPasajeroRepository;
    @Mock
    private VueloRepository mockVueloRepository;

    @BeforeEach
    public void setUp() {
        mockReservaRepository = Mockito.mock(ReservaRepository.class);
        mockPasajeroRepository = Mockito.mock(PasajeroRepository.class);
        mockVueloRepository = Mockito.mock(VueloRepository.class);
        reservaService = new ReservaService(mockReservaRepository, mockPasajeroRepository, mockVueloRepository);
    }

    @Test
    public void crearReservaTest() {
        // Arrange
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L);

        Vuelo vuelo = new Vuelo();
        vuelo.setId(1L);

        when(mockPasajeroRepository.findById(1L)).thenReturn(Optional.of(pasajero));
        when(mockVueloRepository.findById(1L)).thenReturn(Optional.of(vuelo));

        Reserva reservaEsperada = new Reserva();
        when(mockReservaRepository.save(any(Reserva.class))).thenReturn(reservaEsperada);

        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setIdPasajero(pasajero.getId());
        reservaDto.setIdVuelo(vuelo.getId());
        reservaDto.setFechaReserva("2023-11-14");  // Asegúrate de que esta fecha esté en el formato correcto

        // Act
        Reserva nuevaReserva = reservaService.crearReserva(reservaDto);

        // Assert
        assertNotNull(nuevaReserva);
        assertEquals(reservaEsperada, nuevaReserva);
        verify(mockReservaRepository, times(1)).save(any());
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
    public void actualizarReservaTest_ReservaNoExistente() {
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
    public void eliminarReservaTest_ReservaNoExistente() {
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
        ReservaDto reservaDto = new ReservaDto();
        when(mockReservaRepository.save(any())).thenThrow(new RuntimeException());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> reservaService.crearReserva(reservaDto));
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
    public void crearReservaTest_ReservaNull() {
        // Arrange
        ReservaDto reservaDto = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.crearReserva(reservaDto));
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
    public void actualizarReservaTest_IdNull() {
        // Arrange
        Long id = null;
        Reserva reservaActualizada = new Reserva();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.actualizarReserva(id, reservaActualizada));
    }

    @Test
    public void eliminarReservaTest_IdNull() {
        // Arrange
        Long id = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> reservaService.eliminarReserva(id));
    }
}