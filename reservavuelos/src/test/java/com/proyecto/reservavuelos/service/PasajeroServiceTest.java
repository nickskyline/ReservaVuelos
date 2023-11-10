package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.MockitoAnnotations.openMocks;


@ExtendWith(MockitoExtension.class)
class PasajeroServiceTest {

   //1. Mirar cuales son las dependencias que voy a testear
    //2. Donde lo miro ir a POOP en el constructor
    //3. Agregar de pendencia en la clase test
    @Mock
    private PasajeroRepository pasajeroRepository;
    @InjectMocks
    private PasajeroService pasajeroService;
    //Crear la isntancia u objeto de la clase que voy a testear
    @BeforeEach//annotation que me Sirve para ete metodo va a correr ante de que
    // e ejecute cada test
    public void setUp() {
        // mock - que es crear una clase falsa y emular su comportamiento
        MockitoAnnotations.openMocks(this); // Inicializar mocks
        this.pasajeroRepository = mock(PasajeroRepository.class);// Mock del repositorio
        this.pasajeroService= new PasajeroService(pasajeroRepository,Jhonn); // Inicializa el servicio con el mock del repositorio
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

    }

    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/





