package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.repository.CiudadRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CiudadTest {

    @Mock
    CiudadRepository ciudadRepository;

    @InjectMocks
    CiudadService ciudadService;

    @BeforeEach
    public void setUp() {
        ciudadRepository = Mockito.mock(CiudadRepository.class);
        ciudadService = new CiudadService(ciudadRepository);
    }

    @Test
    public void agregarCiudadTest() {
        // Arrange
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Medellín");
        ciudad.setPais("Colombia");

        when(ciudadRepository.save(any(Ciudad.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        Ciudad result = ciudadService.agregarCiudad(ciudad);

        // Assert
        assertEquals(ciudad, result);
        verify(ciudadRepository, times(1)).save(any(Ciudad.class));
    }

    @Test
    public void listarCiudadesTest() {
        // Arrange
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setNombre("Medellín");
        ciudad1.setPais("Colombia");

        Ciudad ciudad2 = new Ciudad();
        ciudad2.setNombre("Bogotá");
        ciudad2.setPais("Colombia");

        when(ciudadRepository.findAll()).thenReturn(Arrays.asList(ciudad1, ciudad2));

        // Act
        List<Ciudad> result = ciudadService.listarCiudades();

        // Assert
        assertEquals(2, result.size());
        verify(ciudadRepository, times(1)).findAll();
    }

    @Test
    public void eliminarCiudadPorIdTest() {
        // Arrange
        Long id = 1L;
        Ciudad ciudad = new Ciudad();
        ciudad.setId(id);
        ciudad.setNombre("Medellín");
        ciudad.setPais("Colombia");

        when(ciudadRepository.findById(id)).thenReturn(Optional.of(ciudad));
        doNothing().when(ciudadRepository).delete(any(Ciudad.class));

        // Act
        boolean result = ciudadService.eliminarCiudadPorId(id);

        // Assert
        assertTrue(result);
        verify(ciudadRepository, times(1)).delete(any(Ciudad.class));
    }

    @Test
    public void eliminarCiudadPorIdNoExistenteTest() {
        // Arrange
        Long id = 1L;

        when(ciudadRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean result = ciudadService.eliminarCiudadPorId(id);

        // Assert
        assertFalse(result);
        verify(ciudadRepository, times(0)).delete(any());
    }

    @Test
    public void actualizarCiudadPorIdTest() {
        // Arrange
        Long id = 1L;
        Ciudad ciudadExistente = new Ciudad();
        ciudadExistente.setId(id);
        ciudadExistente.setNombre("Medellín");
        ciudadExistente.setPais("Colombia");

        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setNombre("Bogotá");
        nuevaCiudad.setPais("Colombia");

        when(ciudadRepository.findById(id)).thenReturn(Optional.of(ciudadExistente));
        when(ciudadRepository.save(any(Ciudad.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        boolean result = ciudadService.actualizarCiudadPorId(id, nuevaCiudad);

        // Assert
        assertTrue(result);
        assertEquals(nuevaCiudad.getNombre(), ciudadExistente.getNombre());
        assertEquals(nuevaCiudad.getPais(), ciudadExistente.getPais());
        verify(ciudadRepository, times(1)).save(ciudadExistente);
    }

    @Test
    public void actualizarCiudadPorIdNoExistenteTest() {
        // Arrange
        Long id = 1L;
        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setNombre("Bogotá");
        nuevaCiudad.setPais("Colombia");

        when(ciudadRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean result = ciudadService.actualizarCiudadPorId(id, nuevaCiudad);

        // Assert
        assertFalse(result);
        verify(ciudadRepository, times(0)).save(any());
    }

    @Test
    public void agregarCiudadConNombreNuloTest() {
        // Arrange
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(null);
        ciudad.setPais("Colombia");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ciudadService.agregarCiudad(ciudad));
    }


    @Test
    public void agregarCiudadConPaisNuloTest() {
        // Arrange
        Ciudad ciudad = new Ciudad();
        ciudad.setNombre("Medellín");
        ciudad.setPais(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ciudadService.agregarCiudad(ciudad));
    }

    @Test
    public void actualizarCiudadConNombreNuloTest() {
        // Arrange
        Long id = 1L;
        Ciudad ciudadExistente = new Ciudad();
        ciudadExistente.setId(id);
        ciudadExistente.setNombre("Medellín");
        ciudadExistente.setPais("Colombia");

        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setNombre(null);
        nuevaCiudad.setPais("Colombia");

        when(ciudadRepository.findById(id)).thenReturn(Optional.of(ciudadExistente));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ciudadService.actualizarCiudadPorId(id, nuevaCiudad));
    }


    @Test
    public void actualizarCiudadConPaisNuloTest() {
        // Arrange
        Long id = 1L;
        Ciudad ciudadExistente = new Ciudad();
        ciudadExistente.setId(id);
        ciudadExistente.setNombre("Medellín");
        ciudadExistente.setPais("Colombia");

        Ciudad nuevaCiudad = new Ciudad();
        nuevaCiudad.setNombre("Bogotá");
        nuevaCiudad.setPais(null);

        when(ciudadRepository.findById(id)).thenReturn(Optional.of(ciudadExistente));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ciudadService.actualizarCiudadPorId(id, nuevaCiudad));
    }


}

