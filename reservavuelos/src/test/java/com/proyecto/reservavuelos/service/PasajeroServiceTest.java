package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Persona;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        this.pasajeroService= new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( ); // Inicializa el servicio con el mock del repositorio

    }
//-----------------------------------*****************----------------------------//

/*1.-testCrearNuevoPasajero*/


    @Test
    void testCrearPasajero() {
        // Arrange
        Pasajero pasajero = new Pasajero(/* create a Pasajero object */);
        when(pasajeroRepository.save(pasajero)).thenReturn(pasajero);

        // Act
        Pasajero createdPasajero = this.pasajeroService.crearPasajero(pasajero);

        // Assert
        // Add your assertions here
        // Verify that the save method was called with the expected arguments
        verify(pasajeroRepository, times(1)).save(pasajero);
    }
    /*2.-test convertirPasajeroDtoAEntidad*/
    /*En estas pruebas, simplemente estamos verificando que los campos tipoPasajero,
    persona, y reservas se copian correctamente de PasajeroDto a Pasajero.*/
    @Test
    public void testConvertirPasajeroDtoAEntidad() {
        // Arrange
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( ); // Crea una instancia de la clase que contiene el método

        Pasajero pasajeroDto = new Pasajero();
        // Configura el pasajeroDto con los datos necesarios

        // Act
        Pasajero pasajeroEntity = pasajeroService.convertirPasajeroDtoAEntidad(pasajeroDto);

        // Assert
        assertNotNull(pasajeroEntity);

    }
    @Test
    public void testConvertirPasajeroDtoAEntidad_WithPersonaAndTipoPasajero() {
        // Arrange
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( ); // Crea una instancia de la clase que contiene el método

        PasajeroDto pasajeroDto = new PasajeroDto();
        pasajeroDto.setTipoPasajero("VIP");
        pasajeroDto.setPersona(new Persona ());

        // Act
        Pasajero pasajeroEntity = this.pasajeroService.convertirPasajeroDtoAEntidad(pasajeroDto);

        // Assert
        assertNotNull(pasajeroEntity);
        assertEquals("VIP", pasajeroEntity.getTipoPasajero());
        assertNotNull(pasajeroEntity.getPersona());
    }
    @Test
    public void testConvertirPasajeroDtoAEntidad_WithNoPersona() {
        // Arrange
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( ); // Crea una instancia de la clase que contiene el método

        PasajeroDto pasajeroDto = new PasajeroDto();
        pasajeroDto.setTipoPasajero("Económico");

        // Act
        Pasajero pasajeroEntity = this.pasajeroService.convertirPasajeroDtoAEntidad(pasajeroDto);

        // Assert
        assertNotNull(pasajeroEntity);
        assertEquals("Económico", pasajeroEntity.getTipoPasajero());
        assertNull(pasajeroEntity.getPersona());
    }
    @Test
    public void testConvertirPasajeroDtoAEntidad_WithNoReservas() {
        // Arrange
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( ); // Crea una instancia de la clase que contiene el método

        PasajeroDto pasajeroDto = new PasajeroDto();
        pasajeroDto.setTipoPasajero("Regular");
        pasajeroDto.setPersona(new Persona());

        // Act
        Pasajero pasajeroEntity = this.pasajeroService.convertirPasajeroDtoAEntidad(pasajeroDto);

        // Assert
        assertNotNull(pasajeroEntity);
        assertEquals("Regular", pasajeroEntity.getTipoPasajero());
        assertNull(pasajeroEntity.getReservas());
        assertNotNull(pasajeroEntity.getPersona());
    }
    /*3.-testActualizar un pasajero existente*/
    @Test
    public void testActualizarDatosPasajeroDesdeDto() {
        // Arrange
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( );
        Pasajero pasajeroExistente = new Pasajero();
        pasajeroExistente.setId(1L); // Supongamos que el pasajero con ID 1 existe en el repositorio
        pasajeroExistente.setTipoPasajero("Tipo Original"); // Establecer un valor original

        // Crear objeto Persona existente
        Persona personaExistente = new Persona();
        pasajeroExistente.setPersona(personaExistente);

        Pasajero pasajeroDto = new Pasajero();
        pasajeroDto.setTipoPasajero("Tipo Actualizado");

        // Crear objeto Persona desde DTO
        Persona personaDesdeDto = new Persona();
        pasajeroDto.setPersona(personaDesdeDto);

        // Act
        this.pasajeroService.actualizarDatosPasajeroDesdeDto(pasajeroExistente, pasajeroDto);

        // Assert
        assertEquals("Tipo Actualizado", pasajeroExistente.getTipoPasajero());

        // Verificar que los objetos de persona estén relacionados
        assertNotNull(pasajeroExistente.getPersona());
        /* assertT(pasajeroExistente, pasajeroExistente.getPersona().getPasajero()); */
    }
    /*4.-testObtenerPasajero*/
    @Test
    public void testObtenerPasajeroPorIdExistente() {
        // Mock del repositorio de pasajeros
        PasajeroRepository pasajeroRepository = mock(PasajeroRepository.class);

        // Crear un objeto de Pasajero para el caso de prueba
        Pasajero pasajero = new Pasajero();
        pasajero.setId(1L); // Establecer el ID del pasajero

        // Configurar el comportamiento del mock para devolver el Pasajero cuando se llame a findById
        when(pasajeroRepository.findById(eq(1L))).thenReturn(Optional.of(pasajero));

        // Crear una instancia de la clase que contiene el método a probar
        PasajeroService pasajeroService = new PasajeroServiceBuilder ( ).setPasajeroRepository (pasajeroRepository).createPasajeroService ( );

        // Llamar al método a probar
        Pasajero resultado = pasajeroService.obtenerPasajeroPorId(1L); // Cambia 1L al ID que desees buscar

        // Verificar que se devolvió el Pasajero esperado
        assertEquals(pasajero, resultado);

        // Verificar que se llamó al método findById del repositorio con el ID correcto
        verify(pasajeroRepository).findById(1L);

        // Verificar que no se lanzó ninguna excepción
        assertDoesNotThrow(() -> pasajeroService.obtenerPasajeroPorId(1L));
    }
    @Test
    public void testObtenerTodosLosPasajeros() {
        // Crear una lista de pasajeros de prueba
        Pasajero pasajero1 = new Pasajero();
        pasajero1.setId(1L);
        pasajero1.setNombre("Pasajero 1");

        Pasajero pasajero2 = new Pasajero();
        pasajero2.setId(2L);
        pasajero2.setNombre("Pasajero 2");

        List<Pasajero> listaPasajeros = new ArrayList<> ();
        listaPasajeros.add(pasajero1);
        listaPasajeros.add(pasajero2);

        // Configurar el comportamiento del repositorio de pasajeros para devolver la lista de pasajeros de prueba
        when(pasajeroRepository.findAll()).thenReturn(listaPasajeros);

        // Llamar al método a probar
        List<Pasajero> resultado = pasajeroService.obtenerTodosLosPasajeros();

        // Verificar que se devolvió la lista de pasajeros esperada
        assertEquals(listaPasajeros, resultado);
    }
    /*1.-testEliminarPasajero*/
    @Test
    public void testEliminarPasajeroExistente() {
        Long pasajeroId = 1L;
        Pasajero pasajero = new Pasajero();
        pasajero.setId(pasajeroId);

        // Configurar el comportamiento del mock del repositorio para que devuelva el pasajero
        when(pasajeroRepository.findById(pasajeroId)).thenReturn(Optional.of(pasajero));

        // Llamar al método a probar
        boolean resultado = pasajeroService.eliminarPasajero(pasajeroId);

        // Verificar que se eliminó el pasajero
        assertTrue(resultado);

        // Verificar que se llamó al método delete del repositorio con el pasajero
        verify(pasajeroRepository).delete(pasajero);
    }

    @Test
    public void testEliminarPasajeroNoExistente() {
        Long pasajeroId = 2L;

        // Configurar el comportamiento del mock del repositorio para que devuelva un Optional vacío
        when(pasajeroRepository.findById(pasajeroId)).thenReturn(Optional.empty());

        // Llamar al método a probar
        boolean resultado = pasajeroService.eliminarPasajero(pasajeroId);

        // Verificar que no se pudo eliminar el pasajero
        assertFalse(resultado);

        // Verificar que no se llamó al método delete del repositorio
        verify(pasajeroRepository, never()).delete(any(Pasajero.class));
    }
    }






