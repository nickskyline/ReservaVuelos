package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.VueloDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import com.proyecto.reservavuelos.repository.CiudadRepository;
import com.proyecto.reservavuelos.repository.VueloRepository;
import com.proyecto.reservavuelos.util.TipoVuelo;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.proyecto.reservavuelos.util.TipoVuelo.PUBLICO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VueloTest {

    @InjectMocks
    private VueloService vueloService;

    @Mock
    private VueloRepository mockVueloRepository;
    @Mock
    private CiudadRepository mockCiudadRepository;

    @Mock
    private AerolineaRepository mockAerolineaRepository;

    @BeforeEach
    public void setUp() {
        mockVueloRepository = Mockito.mock(VueloRepository.class);
        mockCiudadRepository = Mockito.mock(CiudadRepository.class);
        mockAerolineaRepository = Mockito.mock(AerolineaRepository.class);
        vueloService = new VueloService(mockVueloRepository, mockCiudadRepository, mockAerolineaRepository);
    }


    public VueloDto vueloValido() {
        VueloDto vueloDto = new VueloDto();

        vueloDto.setIdCiudadDestino(1L);
        vueloDto.setIdCiudadOrigen(1L);
        vueloDto.setFechaSalida("2023-12-03 10:15");
        vueloDto.setFechaLlegada("2023-12-03 19:15");
        vueloDto.setAsientosDisponibles(50);
        vueloDto.setPrecio(2000D);
        vueloDto.setTipoVuelo(TipoVuelo.CHARTER);
        vueloDto.setIdAerolinea(1L);
        return vueloDto;
    }
    @Test
    public void crearVueloTest() {
        //Arrange

        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");

        when(mockCiudadRepository.findById(vueloDto.getIdCiudadOrigen())).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(vueloDto.getIdCiudadDestino())).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(vueloDto.getIdAerolinea())).thenReturn(Optional.of(aerolinea));

        //Act
        Vuelo nuevoVuelo = vueloService.crearVuelo(vueloDto);

        //Assert
        verify(mockVueloRepository, times(1)).save(any());
    }

    @Test
    public void crearVueloFailTest() {

    }

    @Test
    public void crearVueloSinCiudadDestino() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = null;
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");

        /*
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));
*/
        //Act
        assertThrows(NoSuchElementException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinCiudadOrigen() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Bogota", "Colombia");
        Ciudad ciudadOrigen = null;
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");

        //Act && Assert
        assertThrows(NoSuchElementException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinAerolinea() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Bogota", "Colombia");
        Ciudad ciudadOrigen = new Ciudad(1L, "Barcelona", "Espanha");
        Aerolinea aerolinea = null;
        //Act && Assert
        assertThrows(NoSuchElementException.class, () -> vueloService.crearVuelo(vueloDto));
    }


    @Test
    public void crearVueloSinIdCiudadOrigen() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setIdCiudadOrigen(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinIdCiudadDestino() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setIdCiudadDestino(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinFechaSalida() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setFechaSalida(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinFechaLlegada() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setFechaLlegada(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinAsientosDisponibles() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setAsientosDisponibles(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinPrecio() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setPrecio(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinTipoVuelo() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setTipoVuelo(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

    @Test
    public void crearVueloSinIdAerolinea() {
        //Arrange
        VueloDto vueloDto = vueloValido();

        Ciudad ciudadDestino = new Ciudad(1L, "Barcelona", "Espanha");
        Ciudad ciudadOrigen = new Ciudad(1L, "Bogota", "Colombia");
        vueloDto.setIdAerolinea(null);
        Aerolinea aerolinea = new Aerolinea(1L, "Avianca", "Colombia", "123456890");
        when(mockCiudadRepository.findById(ciudadOrigen.id)).thenReturn(Optional.of(ciudadOrigen));
        when(mockCiudadRepository.findById(ciudadDestino.id)).thenReturn(Optional.of(ciudadDestino));
        when(mockAerolineaRepository.findById(aerolinea.getId())).thenReturn(Optional.of(aerolinea));

        //Act && Assert
        assertThrows(ValidationException.class, () -> vueloService.crearVuelo(vueloDto));
    }

}
