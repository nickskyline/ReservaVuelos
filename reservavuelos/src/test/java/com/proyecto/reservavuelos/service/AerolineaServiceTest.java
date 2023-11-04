package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AerolineaServiceTest {
     //1. Mirar cuale son las dependencias que voy a testear
    //2. Donde lo miro ir a POOP en el constructor
    //3. Agregar de pendencia en la clase test
     @Mock
    private AerolineaRepository aerolineaRepository;
    @InjectMocks
    private AerolineaService aerolineaService;
    //Crear la isntancia u objeto de la clase que voy a testear
        @BeforeEach//annotation que me Sirve para ete metodo va a correr ante de que e ejecute cada test
    public void setUp() {
            // mock - que es crear una clase falsa y emular su comportamiento
            this.aerolineaRepository = mock(AerolineaRepository.class);// Mock del repositorio
            MockitoAnnotations.openMocks(this);
         this.aerolineaService= new   AerolineaService(aerolineaRepository); // Inicializa el servicio con el mock del repositorio
    }

    @AfterEach
    void tearDown() {
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
/*esta prueba se asegura de que el servicio de aerolíneas sea capaz de
guardar una aerolínea en el repositorio y de que la aerolínea devuelta sea
la misma que la que se intentó guardar. Si ambas aserciones pasan con éxito, la prueba
 se considera exitosa y confirma el funcionamiento correcto de esta parte del servicio.*/
    @Test
    public void crearAerolineaDeberiaGuardarAerolineaEnElRepositorio() {
        // Arrange
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Aerolínea de Prueba");

        // Configura el comportamiento del repositorio al guardar la aerolínea
        when(aerolineaRepository.save(aerolinea)).thenReturn(aerolinea);

        // Act
        Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolinea);

        // Assert
        // Verificar que el método save del repositorio haya sido llamado con la aerolínea proporcionada
        verify(aerolineaRepository).save(aerolinea);

        // Verificar que la aerolínea creada sea igual a la aerolínea original
        assertEquals(aerolinea, aerolineaCreada);
    }

    /*En esta prueba, estamos probando el escenario en el que se proporciona
     un nombre nulo para la aerolínea. La prueba espera que se lance una excepción
     IllegalArgumentException con el mensaje correcto. Si el método crearAerolinea
     maneja adecuadamente los nombres nulos y lanza una excepción en este caso,
      la prueba pasará.*/
    @Test
    public void crearAerolineaDeberiaValidarNombreNoNuloOVacio() {
        // Arrange
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(null);  // Establecer el nombre como nulo

        // Act y Assert
        try {
            Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolinea);
            fail("Se esperaba una excepción");  // La prueba fallará si no se lanza una excepción
        } catch (IllegalArgumentException e) {
            // Verificar que se lanzó una excepción con el mensaje esperado
            assertEquals("El nombre de la aerolínea no es válido", e.getMessage());
        }
    }
/*esta prueba se asegura de que el servicio de aerolíneas sea capaz de crear y
 guardar una nueva aerolínea en el repositorio cuando se le proporciona una aerolínea
 con un nombre válido. Si todas las aserciones pasan con éxito, la prueba se considera
 exitosa y confirma el funcionamiento correcto del servicio en este escenario.*/
    @Test
    public void crearAerolineaDeberiaSerExitoso() {
        // Arrange
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Aerolínea Válida");

        // Simular el comportamiento del repositorio para no devolver una aerolínea con el mismo nombre
        when(aerolineaRepository.findByNombre(aerolinea.getNombre()))
                .thenReturn(null);

        // Simular el comportamiento del repositorio al guardar la aerolínea
        when(aerolineaRepository.save(aerolinea))
                .thenReturn(aerolinea);

        // Act
        Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolinea);

        // Assert
        // Verificar que el método save del repositorio se llamó exactamente una vez
        verify(aerolineaRepository, times(1)).save(aerolinea);

        // Verificar que la aerolínea creada no sea nula y sea igual a la aerolínea original
        assertNotNull(aerolineaCreada);
        assertEquals(aerolinea, aerolineaCreada);
    }

    /*En esta prueba, primero configuramos el comportamiento del
    repositorio para que cuando se llame al método findAll(),
    retorne una lista simulada de aerolíneas. Luego, llamamos al método obtenerTodasLasAerolineas()
    y verificamos que el resultado sea igual a la lista simulada.*/
    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarListaDeAerolineas() {
        // Arrange
        List<Aerolinea> aerolineas = new ArrayList<> ();
        aerolineas.add(new Aerolinea("Aerolínea 1"));
        aerolineas.add(new Aerolinea("Aerolínea 2"));

        // Configura el comportamiento del repositorio para que retorne la lista de aerolíneas simulada
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Act
        List<Aerolinea> aerolineasObtenidas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        // Verifica que el método findAll del repositorio haya sido llamado
        verify(aerolineaRepository).findAll();

        // Verifica que la lista de aerolíneas obtenida sea igual a la lista simulada
        assertEquals(aerolineas, aerolineasObtenidas);
    }
    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarTodasLasAerolineas() {
        // Arrange
        List<Aerolinea> aerolineasEsperadas = Arrays.asList(
                new Aerolinea("Aerolínea 1"),
                new Aerolinea("Aerolínea 2"),
                new Aerolinea("Aerolínea 3")
        );

        when(aerolineaRepository.findAll()).thenReturn(aerolineasEsperadas);

        // Act
        List<Aerolinea> aerolineasObtenidas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).hasSize(aerolineasEsperadas.size());
        assertThat(aerolineasObtenidas).containsExactlyInAnyOrderElementsOf(aerolineasEsperadas);
    }
    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarAerolineasOrdenadasAlfabeticamente() {
        // Arrange
        List<Aerolinea> aerolineasDesordenadas = Arrays.asList(
                new Aerolinea("Delta Airlines"),
                new Aerolinea("American Airlines"),
                new Aerolinea("Southwest Airlines")
        );

        // Simula que el repositorio devuelve las aerolíneas desordenadas
        when(aerolineaRepository.findAll()).thenReturn(aerolineasDesordenadas);

        // Act
        List<Aerolinea> aerolineasOrdenadas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineasOrdenadas).isNotNull();
        assertThat(aerolineasOrdenadas).hasSize(aerolineasDesordenadas.size());
        assertThat(aerolineasOrdenadas).containsExactly(
                new Aerolinea("American Airlines"),
                new Aerolinea("Delta Airlines"),
                new Aerolinea("Southwest Airlines")
        );
    }
    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarListaVaciaSiNoHayAerolineas() {
        // Arrange
        when(aerolineaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Aerolinea> aerolineas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineas).isNotNull();
        assertThat(aerolineas).isEmpty();
    }
    @Test
    public void obtenerAerolineasPorPaisDeberiaRetornarAerolineasDelaCiudadEspecificado() {
        // Arrange
        List<Aerolinea> aerolineas = Arrays.asList(
                new Aerolinea("Delta Airlines", "Estados Unidos"),
                new Aerolinea("British Airways", "Reino Unido"),
                new Aerolinea("Air France", "Francia"),
                new Aerolinea("Lufthansa", "Alemania")
        );

        // Simula que el repositorio devuelve todas las aerolíneas
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Act
        List<Aerolinea> aerolineasEstadosUnidos = aerolineaService.obtenerAerolineasPorPais("Estados Unidos");

        // Assert
        assertThat(aerolineasEstadosUnidos).isNotNull();
        assertThat(aerolineasEstadosUnidos).hasSize(1);
        assertThat(aerolineasEstadosUnidos.get(0).getNombre()).isEqualTo("Delta Airlines");
    }

    @Test
    public void obtenerAerolineasPorPaisDeberiaRetornarListaVaciaSiNoHayAerolineasDelPaisEspecificado() {
        // Arrange
        List<Aerolinea> aerolineas = Arrays.asList(
                new Aerolinea("British Airways", "Reino Unido"),
                new Aerolinea("Air France", "Francia"),
                new Aerolinea("Lufthansa", "Alemania")
        );

        // Simula que el repositorio devuelve aerolíneas de otros países
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Act
        List<Aerolinea> aerolineasEstadosUnidos = aerolineaService.obtenerAerolineasPorCiudad("Estados Unidos");

        // Assert
        assertThat(aerolineasEstadosUnidos).isNotNull();
        assertThat(aerolineasEstadosUnidos).isEmpty();
    }

}






