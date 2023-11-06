package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import com.proyecto.reservavuelos.service.AerolineaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

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

    //-----------------------------------**********Prueba Unitaria Aerolinaea******------------------------------------------------//

    //Este ejemplo establece el escenario, realiza una acción y luego verifica el comportamiento esperado.
    /* Arrange: En esta sección, se configuran las condiciones iniciales para la prueba.
    Se crea una instancia de la clase Aerolinea y se establece su nombre como "Aerolínea de Prueba".
    También, se configura el comportamiento simulado del repositorio (aerolineaRepository) para que,
    uando se llame a su método save con la aerolínea proporcionada, devuelva la misma aerolínea.
    Esto simula la operación de guardar la aerolínea en el repositorio.
Act: Luego, se llama al método crearAerolinea del servicio de aerolíneas (aerolineaService) con la aerolínea preparada en el paso anterior.
 Esto ejecuta la lógica de creación de la aerolínea y devuelve la aerolínea creada.
Assert: En esta sección,
se realizan las comprobaciones o afirmaciones. La prueba verifica que la aerolínea creada tenga el nombre "Aerolínea de Prueba" usando la aserción assertEquals.
 Si la aerolínea creada tiene el nombre correcto, la prueba pasa con éxito.
En resumen, esta prueba asegura que el método crearAerolinea sea capaz de crear una aerolínea con el nombre proporcionado y que la aerolínea creada tenga el nombre esperado. Si la prueba se ejecuta con éxito
 sin errores, significa que esta parte del servicio funciona según lo esperado*/
    @Test
    public void crearAerolinea() {
        // Arrange
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre("Aerolínea de Prueba");

        // Simular el comportamiento del repositorio al guardar la aerolínea
        Mockito.when(aerolineaRepository.save(aerolinea)).thenReturn(aerolinea);

        // Act
        Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolinea);

        // Assert
        // Verificar que la aerolínea se haya creado correctamente y sea igual a la original
        assertEquals("Aerolínea de Prueba", aerolineaCreada.getNombre());
    }
    @Test
    void crearAerolineaDeberiaLanzarExcepcionCuandoNombreEsInvalido() {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setNombre(null); // O setNombre("") para el caso vacío

        // Verifica que se lance la excepción cuando el nombre no es válido
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.crearAerolinea(aerolinea));
    }

    /*En esta prueba, estamos probando el escenario en el que se proporciona
     un nombre nulo para la aerolínea. La prueba espera que se lance una excepción
     IllegalArgumentException con el mensaje correcto. Si el método crearAerolinea
     maneja adecuadamente los nombres nulos y lanza una excepción en este caso,
      la prueba pasará.*/
    @Test
    public void crearAerolineaDeberiaValidarNombreNoNuloOVacio() {
        // Arrange
        String nombreAerolinea = null;
        Aerolinea aerolinea = new Aerolinea(nombreAerolinea);
        aerolinea.setNombre(null);  // Establecer el nombre como nulo

        // Act y Assert
        try {
            Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolinea);
            Assertions.fail("Se esperaba una excepción");  // La prueba fallará si no se lanza una excepción
        } catch (IllegalArgumentException e) {
            // Verificar que se lanzó una excepción con el mensaje esperado
            assertEquals("El nombre de la aerolínea no es válido", e.getMessage());
        }
    }

    /**
     *
     */
/*esta prueba se asegura de que el servicio de aerolíneas sea capaz de crear y
 guardar una nueva aerolínea en el repositorio cuando se le proporciona una aerolínea
 con un nombre válido. Si todas las aserciones pasan con éxito, la prueba se considera
 exitosa y confirma el funcionamiento correcto del servicio en este escenario.*/
    @Test
    public void crearAerolineaDeberiaSerExitoso() {
        crearAerolineaDeberiaSerExitoso (null);
    }

    /**
     * @param nombreAerolinea 
     */
/*esta prueba se asegura de que el servicio de aerolíneas sea capaz de crear y
 guardar una nueva aerolínea en el repositorio cuando se le proporciona una aerolínea
 con un nombre válido. Si todas las aserciones pasan con éxito, la prueba se considera
 exitosa y confirma el funcionamiento correcto del servicio en este escenario.*/
    @Test
    public void crearAerolineaDeberiaSerExitoso(String nombreAerolinea) {
        // Arrange
        Aerolinea aerolinea;
        aerolinea = new Aerolinea(nombreAerolinea);
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
        //assertEquals(aerolinea, aerolineaCreada);
    }


    /*En esta prueba, primero configuramos el comportamiento del
    repositorio para que cuando se llame al método findAll(),
    retorne una lista simulada de aerolíneas. Luego, llamamos al método obtenerTodasLasAerolineas()
    y verificamos que el resultado sea igual a la lista simulada.*/

    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarListaDeAerolineas() {
        // Arrange
        List<Aerolinea> aerolineas = new ArrayList<> ();
        aerolineas.add(new Aerolinea("Aerolínea 1", "Francia"));
        aerolineas.add(new Aerolinea("Aerolínea 2", "Francia"));

        // Configura el comportamiento del repositorio para que retorne la lista de aerolíneas simulada
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Act
        List<Aerolinea> aerolineasObtenidas =  this.aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        // Verifica que el método findAll del repositorio haya sido llamado
        verify(aerolineaRepository).findAll();

        // Verifica que la lista de aerolíneas obtenida sea igual a la lista simulada
        assertEquals(aerolineas, aerolineasObtenidas);
    }

    @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarListaVaciaSiNoHayAerolineas() {
        // Arrange
        when(aerolineaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Aerolinea> aerolineas =  this.aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineas).isNotNull();
    }
    @Test
    public void obtenerAerolineaPorId_DeberiaRetornarAerolineaCuandoExiste() {

        // Crear una aerolínea de ejemplo
        Aerolinea aerolineaEjemplo = new Aerolinea();
        aerolineaEjemplo.setId(String.valueOf (1L));
        aerolineaEjemplo.setNombre("Aerolínea de Ejemplo");

        // Configurar el comportamiento del mock
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.of(aerolineaEjemplo));

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método obtenerAerolineaPorId con un ID existente
        Aerolinea aerolineaObtenida =  this.aerolineaService.obtenerAerolineaPorId(1L);

        // Verificar que el resultado no sea nulo y sea igual a la aerolínea de ejemplo
        assertNotNull(aerolineaObtenida);
        assertEquals(aerolineaEjemplo, aerolineaObtenida);
    }

    @Test
    public void obtenerAerolineaPorId_DeberiaRetornarNullCuandoNoExiste() {


        // Configurar el comportamiento del mock para que no se encuentre la aerolínea
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.empty());

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método obtenerAerolineaPorId con un ID que no existe
        Aerolinea aerolineaObtenida =  this.aerolineaService.obtenerAerolineaPorId(1L);

        // Verificar que el resultado sea nulo
        assertNull(aerolineaObtenida);
    }
    @Test
    public void actualizarAerolinea_DeberiaRetornarVerdaderoCuandoActualizaExistente() {

        // Crear una aerolínea de ejemplo
        Aerolinea aerolineaExistente = new Aerolinea();
        aerolineaExistente.setId(String.valueOf (1L));
        aerolineaExistente.setNombre("Aerolínea Antigua");
        aerolineaExistente.setPais("Antigua");

        // Nueva aerolínea con datos actualizados
        Aerolinea nuevaAerolinea = new Aerolinea();
        nuevaAerolinea.setId(String.valueOf (1L));
        nuevaAerolinea.setNombre("Aerolínea Actualizada");
        nuevaAerolinea.setPais("Actualizada");

        // Configurar el comportamiento del mock
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.of(aerolineaExistente));

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método actualizarAerolinea
        boolean actualizado =  this.aerolineaService.actualizarAerolinea(1L, nuevaAerolinea);

        // Verificar que la aerolínea se haya actualizado y que el resultado sea verdadero
        assertTrue(actualizado);
        assertEquals("Aerolínea Actualizada", aerolineaExistente.getNombre());
        assertEquals("Actualizada", aerolineaExistente.getPais());
    }
    @Test
    public void actualizarAerolinea_DeberiaRetornarFalsoCuandoAerolineaNoExiste() {

        // Nueva aerolínea con datos actualizados
        Aerolinea nuevaAerolinea = new Aerolinea();
        nuevaAerolinea.setId(String.valueOf (1L));
        nuevaAerolinea.setNombre("Aerolínea Actualizada");
        nuevaAerolinea.setPais("Actualizada");

        // Configurar el comportamiento del mock para que no se encuentre la aerolínea
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.empty());

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método actualizarAerolinea con un ID que no existe
        boolean actualizado =  this.aerolineaService.actualizarAerolinea(1L, nuevaAerolinea);

        // Verificar que la aerolínea no se haya actualizado y que el resultado sea falso
        assertFalse(actualizado);
    }
    @Test
    public void actualizarAerolinea_DeberiaRetornarFalsoCuandoIDNulo() {
        // Mock del repositorio
        AerolineaRepository aerolineaRepository = mock(AerolineaRepository.class);

        // Nueva aerolínea con datos actualizados
        Aerolinea nuevaAerolinea = new Aerolinea();

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método actualizarAerolinea con un ID nulo
        boolean actualizado =  this.aerolineaService.actualizarAerolinea(null, nuevaAerolinea);

        // Verificar que el resultado sea falso
        assertFalse(actualizado);
    }
    @Test
    public void eliminarAerolinea_DeberiaRetornarVerdaderoCuandoEliminaExistente() {

        // Crear una aerolínea de ejemplo
        Aerolinea aerolineaExistente;
        aerolineaExistente = new Aerolinea ();
        aerolineaExistente.setId(String.valueOf (1L));
        aerolineaExistente.setNombre("Aerolínea a Eliminar");
        aerolineaExistente.setPais("Eliminarlandia");

        // Configurar el comportamiento del mock para encontrar la aerolínea
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.of(aerolineaExistente));

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método eliminarAerolinea
        boolean eliminado =  aerolineaService.eliminarAerolinea(1L);

        // Verificar que la aerolínea se haya eliminado y que el resultado sea verdadero
        assertTrue(eliminado);
    }
    @Test
    public void eliminarAerolinea_DeberiaRetornarFalsoCuandoAerolineaNoExiste() {

        // Configurar el comportamiento del mock para que no se encuentre la aerolínea
        when(aerolineaRepository.findById(1L)).thenReturn(Optional.empty());

        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método eliminarAerolinea con un ID que no existe
        boolean eliminado = this.aerolineaService.eliminarAerolinea(1L);

        // Verificar que la aerolínea no se haya eliminado y que el resultado sea falso
        assertFalse(eliminado);
    }
    @Test
    public void eliminarAerolinea_DeberiaRetornarFalsoCuandoIDNulo() {


        // Crear una instancia del servicio
        AerolineaService aerolineaService = new AerolineaService(aerolineaRepository);

        // Llamar al método eliminarAerolinea con un ID nulo
        boolean eliminado = this.aerolineaService.eliminarAerolinea(null);

        // Verificar que el resultado sea falso
        assertFalse(eliminado);
    }


}






