package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Persona;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;

import java.util.Optional;

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
        this.pasajeroService= new PasajeroService( ); // Inicializa el servicio con el mock del repositorio

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
        PasajeroService pasajeroService = new PasajeroService(); // Crea una instancia de la clase que contiene el método

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
        PasajeroService pasajeroService = new PasajeroService(); // Crea una instancia de la clase que contiene el método

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
        PasajeroService pasajeroService = new PasajeroService(); // Crea una instancia de la clase que contiene el método

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
        PasajeroService pasajeroService = new PasajeroService(); // Crea una instancia de la clase que contiene el método

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
        PasajeroService pasajeroService = new PasajeroService();
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
        /* assertSame(pasajeroExistente, pasajeroExistente.getPersona().getPasajero()); */
    }



}

    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/





