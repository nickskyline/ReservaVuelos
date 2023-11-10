package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import com.proyecto.reservavuelos.service.PasajeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PasajeroServiceTest {

   //1. Mirar cuales son las dependencias que voy a testear
    //2. Donde lo miro ir a POOP en el constructor
    //3. Agregar de pendencia en la clase test
    @Mock
    private PasajeroRepository pasajeroRepository;
    @InjectMocks
    private PasajeroService pasajeroService;
    private Pasajero pasajeroMock;

    //Crear la isntancia u objeto de la clase que voy a testear
    @BeforeEach//annotation que me Sirve para ete metodo va a correr ante de que
    // e ejecute cada test
    public void setUp() {
        // mock - que es crear una clase falsa y emular su comportamiento
        MockitoAnnotations.openMocks(this); // Inicializar mocks
        this.pasajeroRepository = mock(PasajeroRepository.class);// Mock del repositorio
        this.pasajeroService= new PasajeroService(pasajeroRepository); // Inicializa el servicio con el mock del repositorio

    }


// Arrange debe preparar lo tet variable o parametros que usted va testera
// (Preparar): En esta sección, debes configurar el escenario para la prueba.
// Esto incluye crear instancias de objetos,
// establecer estados iniciales y configurar cualquier dependencia que sea necesaria.

// Act (Actuar): En esta sección, debes realizar la acción o el método que deseas probar.

// Assert (Afirmar): En esta sección, debes realizar afirmaciones para verificar
// que el método actúa como se espera. Puedes usar aserciones
// de JUnit o cualquier otra biblioteca de aserciones para realizar las comprobaciones.
//-----------------------------------*****************----------------------------//

/*1.-testCrearNuevoPasajero*/
@Test
public void testCrearPasajero() {
    // Datos de prueba
    PasajeroDto pasajeroDto = new PasajeroDto();
    pasajeroDto.setNombre("John");
    pasajeroDto.setApellido("Doe");

    Pasajero pasajeroMock = new Pasajero();
    pasajeroMock.setEdad(30);
    pasajeroMock.setNombre("John");
    pasajeroMock.setApellido("Doe");

    // Configuración del repositorio mock
    Mockito.when(pasajeroRepository.save(any(Pasajero.class))).thenReturn(pasajeroMock);

    // Llamada al método del servicio
    Pasajero pasajeroCreado = pasajeroService.crearPasajero(pasajeroDto);

    // Verificaciones
    assertNotNull(pasajeroCreado);
    assertEquals(pasajeroDto.getNombre(), pasajeroCreado.getNombre());
    assertEquals(pasajeroDto.getApellido(), pasajeroCreado.getApellido());
    assertEquals(pasajeroMock.getEdad(), pasajeroCreado.getEdad());
}
        @Test
        public void testObtenerPasajeroPorId() {
            // Arrange
            Long id = 1L;
            Pasajero pasajeroMock = new Pasajero();
            pasajeroMock.setId(id);

            // Configuración del repositorio mock
            when(pasajeroRepository.findById(id)).thenReturn(Optional.of(pasajeroMock));

            // Act
            Pasajero pasajeroObtenido = this.pasajeroService.obtenerPasajeroPorId(id);

            // Assert
            assertNotNull(pasajeroObtenido);
            assertEquals(id, pasajeroObtenido.getId());
        }
    @Test
    public void testObtenerPasajeroPorIdCuandoNoExiste() {
        // Arrange
        Long idNoExistente = 2L;

        // Configuración del repositorio mock para devolver Optional vacío
        when(pasajeroRepository.findById(idNoExistente)).thenReturn(Optional.empty());

        // Act
        Pasajero pasajeroObtenido = pasajeroService.obtenerPasajeroPorId(idNoExistente);

        // Assert
        assertNull(pasajeroObtenido);
    }
    @Test
    public void testObtenerTodosLosPasajeros() {
        // Arrange
        List<Pasajero> pasajerosMock = Arrays.asList(
                new Pasajero(1L, "John", "Doe", 30),
                new Pasajero(2L, "Jane", "Smith", 25)
        );

        // Configuración del repositorio mock
        when(pasajeroRepository.findAll()).thenReturn(pasajerosMock);

        // Act
        List<PasajeroDto> pasajerosDtoObtenidos = this.pasajeroService.obtenerTodosLosPasajeros();

        // Assert
        assertNotNull(pasajerosDtoObtenidos);
        assertEquals(pasajerosMock.size(), pasajerosDtoObtenidos.size());

        for (int i = 0; i < pasajerosMock.size(); i++) {
            Pasajero pasajero = pasajerosMock.get(i);
            PasajeroDto pasajeroDto = pasajerosDtoObtenidos.get(i);

            assertEquals(pasajero.getId(), pasajeroDto.getId());
            assertEquals(pasajero.getNombre(), pasajeroDto.getNombre());
            assertEquals(pasajero.getApellido(), pasajeroDto.getApellido());
            assertEquals(pasajero.getEdad(), pasajeroDto.getEdad());
        }
    }
}












/*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/





