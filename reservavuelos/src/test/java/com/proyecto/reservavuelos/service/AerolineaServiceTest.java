package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.proyecto.reservavuelos.dto.AerolineaDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class AerolineaServiceTest {
     //1. Mirar cuale son las dependencias que voy a testear
    //2. Donde lo miro ir a POOP en el constructor
    //3. Agregar de pendencia en la clase test
     @Mock
    private AerolineaRepository aerolineaRepository;
    @InjectMocks
    private AerolineaService aerolineaService;
    private String nuevaYork;

    //Crear la isntancia u objeto de la clase que voy a testear
        @BeforeEach//annotation que me Sirve para ete metodo va a correr ante de que e ejecute cada test
    public void setUp() {
            // mock - que es crear una clase falsa y emular su comportamiento
            this.aerolineaRepository = mock(AerolineaRepository.class);// Mock del repositorio
            MockitoAnnotations.openMocks(this);
         this.aerolineaService= new   AerolineaService(aerolineaRepository, nuevaYork); // Inicializa el servicio con el mock del repositorio
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

/*1.-testCrearNuevaAerolinea*/
    @Test
    public void testCrearNuevaAerolineaConDto() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea Nueva", "Francia", "4444224");

        // Crea una instancia de Aerolinea basada en el DTO
        Aerolinea nuevaAerolinea = new Aerolinea( );
        nuevaAerolinea.setNombre(aerolineaDto.getNombre());
        nuevaAerolinea.setPais(aerolineaDto.getPais());
        nuevaAerolinea.setTelefono(aerolineaDto.getTelefono());

        // Mock de aerolineaRepository para simular que la aerolínea no existe en la base de datos
        when(aerolineaRepository.existsByNombre(aerolineaDto.getNombre())).thenReturn(false);

        // Configura el comportamiento del repositorio al guardar la aerolínea
        when(aerolineaRepository.save(nuevaAerolinea)).thenReturn(nuevaAerolinea);

        // Llamada al método a probar
        Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolineaDto);

        // Verificaciones
        assertEquals("Aerolínea Nueva", aerolineaCreada.getNombre());
        assertEquals("Francia", aerolineaCreada.getPais());
        assertEquals("4444224", aerolineaCreada.getTelefono());

        // Verificar que el método existsByNombre se llamó una vez con el nombre correcto
        verify(aerolineaRepository, times(1)).existsByNombre("Aerolínea Nueva");

        // Verificar que el método save se llamó una vez con la aerolínea creada
        verify(aerolineaRepository, times(1)).save(nuevaAerolinea);
    }

    @Test
    public void crearAerolineaDeberiaGuardarAerolineaEnElRepositorio() {
        // Arrange
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea de Prueba", "Pais de Prueba", "1234567890");
        Aerolinea aerolinea = new Aerolinea( );
        aerolinea.setNombre(aerolineaDto.getNombre());
        aerolinea.setPais(aerolineaDto.getPais());
        aerolinea.setTelefono(aerolineaDto.getTelefono());

        // Configura el comportamiento del repositorio al guardar la aerolínea
        when(aerolineaRepository.save(aerolinea)).thenReturn(aerolinea);

        // Act
        Aerolinea aerolineaCreada = this.aerolineaService.crearAerolinea(aerolineaDto);

        // Assert
        // Verificar que el método save del repositorio haya sido llamado con la aerolínea proporcionada
        verify(aerolineaRepository).save(aerolinea);

        // Verificar que la aerolínea creada sea igual a la aerolínea original
        assertEquals(aerolinea, aerolineaCreada);
    }
    @Test
    public void testCrearNuevaAerolineaValidandoNombre() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto(null, "Francia", "4444224");

        // Act y Assert
        try {
            this.aerolineaService.crearAerolinea(aerolineaDto);
            fail("Se esperaba una excepción");  // La prueba fallará si no se lanza una excepción
        } catch (IllegalArgumentException e) {
            // Verificar que se lanzó una excepción con el mensaje esperado
            assertEquals("El nombre de la aerolínea no es válido", e.getMessage());
        }
    }
    /*@Test
    public void testCrearNuevaAerolineaExitosa() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea Válida", "Francia", "4444224");

        // Simular el comportamiento del repositorio para no devolver una aerolínea con el mismo nombre
        when(aerolineaRepository.existsByNombre(aerolineaDto.getNombre()))
                .thenReturn(false);

        // Llamada al método a probar
        Aerolinea nuevaAerolinea = this.aerolineaService.crearAerolinea(aerolineaDto);

        // Verificaciones
        assertEquals("Aerolínea Válida", nuevaAerolinea.getNombre());
        assertEquals("Francia", nuevaAerolinea.getPais());
        assertEquals("4444224", nuevaAerolinea.getTelefono());

        // Verificar que el método existsByNombre se llamó una vez con el nombre correcto
        verify(aerolineaRepository, times(1)).existsByNombre("Aerolínea Válida");

        // Verificar que el método save se llamó una vez
        verify(aerolineaRepository, times(1)).save(any(Aerolinea.class));
    }*/

    @Test
    public void testCrearAerolineaNombreInvalido() {
        // Arrange
        AerolineaDto aerolineaDto = new AerolineaDto("", "Francia", "4444224");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.crearAerolinea(aerolineaDto));
    }

    @Test
    public void testCrearAerolineaNombreExistente() {
        // Arrange
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea 2", "Francia", "4444224");

        when(aerolineaRepository.existsByNombre("Aerolínea 2")).thenReturn(true);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.crearAerolinea(aerolineaDto));
    }
/*2.-@Test tObtenerLaAerolinea*/
    public void testObtenerTodasLasAerolineas() {
        // Datos de ejemplo para la prueba
        List<Aerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new Aerolinea("Aerolínea 1"));
        aerolineas.add(new Aerolinea("Aerolínea 2"));

        // Mock de aerolineaRepository para simular el comportamiento de findAll
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Llamada al método a probar
        List<Aerolinea> result = aerolineaService.obtenerTodasLasAerolineas();

        // Verificar que el método findAll se llamó una vez
        verify(aerolineaRepository, times(1)).findAll();

        // Verificar que el resultado de la prueba es igual a la lista de aerolíneas simulada
        assertEquals(aerolineas, result);
    }
    @Test
    public void testObtenerTodasLasAerolineasCuandoHayAerolineas() {
        // Crear una lista de aerolíneas simuladas
        List<Aerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new Aerolinea("Aerolínea 1"));
        aerolineas.add(new Aerolinea("Aerolínea 2"));

        // Configuración del repositorio para que devuelva la lista simulada
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Llamada al método a probar
        List<Aerolinea> result = aerolineaService.obtenerTodasLasAerolineas();

        // Verificar que el método findAll se llamó una vez
        verify(aerolineaRepository, times(1)).findAll();

        // Verificar que el resultado es la lista de aerolíneas simuladas
        assertEquals(aerolineas, result);
    }


    @Test
    public void testObtenerTodasLasAerolineasCuandoNoHayAerolineas() {
        // Configuración del repositorio para que devuelva una lista vacía
        when(aerolineaRepository.findAll()).thenReturn(Collections.emptyList());

        // Llamada al método a probar
        List<Aerolinea> result = aerolineaService.obtenerTodasLasAerolineas();

        // Verificar que el método findAll se llamó una vez
        verify(aerolineaRepository, times(1)).findAll();

        // Verificar que el resultado es una lista vacía
        assertEquals(Collections.emptyList(), result);
    }  @Test
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
    public void testObtenerAerolineaPorIdExistente() {
        // ID de la aerolínea que se busca
        Long aerolineaId = 1L;

        // Aerolínea simulada que se espera encontrar en la base de datos
        Aerolinea aerolineaSimulada = new Aerolinea("Aerolínea 1");
        when(aerolineaRepository.findById(aerolineaId)).thenReturn(Optional.of(aerolineaSimulada));

        // Llamada al método a probar
        Aerolinea result = aerolineaService.obtenerAerolineaPorId(aerolineaId);

        // Verificar que el método findById se llamó una vez con el ID esperado
        verify(aerolineaRepository, times(1)).findById(aerolineaId);

        // Verificar que el resultado es igual a la aerolínea simulada
        assertEquals(aerolineaSimulada, result);
    } @Test
    public void testObtenerTodasLasAerolineasDeberiaRetornarAerolineasOrdenadasAlfabeticamente() {
        // Arrange
        List<Aerolinea> aerolineasDesordenadas = Arrays.asList(
                new Aerolinea( ),
                new Aerolinea( ),
                new Aerolinea( )
        );

        List<Aerolinea> aerolineasOrdenadas = Arrays.asList(
                new Aerolinea( ),
                new Aerolinea( ),
                new Aerolinea( )
        );

        // Simula que el repositorio devuelve las aerolíneas ordenadas alfabéticamente
        when(aerolineaRepository.findAll()).thenReturn(aerolineasOrdenadas);

        // Act
        List<Aerolinea> aerolineasObtenidas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).hasSize(aerolineasDesordenadas.size());
        assertThat(aerolineasObtenidas).containsExactlyElementsOf(aerolineasOrdenadas);
    }

    /*3.-Obtener aerolinea por id*/

    @Test
    public void testObtenerAerolineaPorIdNoExistente() {
        // ID de la aerolínea que se busca
        Long aerolineaId = 1L;

        // No se encuentra la aerolínea en la base de datos
        when(aerolineaRepository.findById(aerolineaId)).thenReturn(Optional.empty());

        // Llamada al método a probar
        Aerolinea result = aerolineaService.obtenerAerolineaPorId(aerolineaId);

        // Verificar que el método findById se llamó una vez con el ID esperado
        verify(aerolineaRepository, times(1)).findById(aerolineaId);

        // Verificar que el resultado es nulo (ya que no se encontró la aerolínea)
        assertNull(result);
    }
/*4Actualizar Aerolinea*/
    @Test
    public void testActualizarAerolineaInexistente() {
        // ID de una aerolínea inexistente
        Long aerolineaId = 2L;

        // Nueva aerolínea con datos actualizados
        AerolineaDto nuevaAerolinea = new Aerolinea( );
        nuevaAerolinea.setNombre("Aerolínea Actualizada");

        // Cuando se busca la aerolínea por ID, no se encuentra (retorna Optional vacío)
        when(aerolineaRepository.findById(aerolineaId)).thenReturn(Optional.empty());

        // Llamada al método a probar
        boolean resultado = aerolineaService.actualizarAerolinea(aerolineaId, nuevaAerolinea);

        // Verificar que el método findById se llamó una vez con el ID esperado
        verify(aerolineaRepository, times(1)).findById(aerolineaId);

        // No se debe llamar al método save en este caso
        verify(aerolineaRepository, never()).save(any());

        // Verificar que la operación falló (debería devolver false)
        assertFalse(resultado);
    }
    @Test
    public void testActualizarAerolineaExitosa() {
        // Arrange
        Long id = 1L;
        Aerolinea aerolineaExistente = new Aerolinea( );
        aerolineaExistente.setId(String.valueOf (id));
        aerolineaExistente.setNombre("Aerolínea Antigua");
        aerolineaExistente.setPais("España");

        Aerolinea nuevaAerolinea = new Aerolinea( );
        nuevaAerolinea.setNombre("Aerolínea Actualizada");
        nuevaAerolinea.setPais("Francia");

        when(aerolineaRepository.findById(id)).thenReturn(Optional.of(aerolineaExistente));
        when(aerolineaRepository.save(aerolineaExistente)).thenReturn(aerolineaExistente);

        // Act
        boolean resultado = this.aerolineaService.actualizarAerolinea(id, nuevaAerolinea);

        // Assert
        assertTrue(resultado);
        assertEquals("Aerolínea Actualizada", aerolineaExistente.getNombre());
        assertEquals("Francia", aerolineaExistente.getPais());
    }

    @Test
    public void testActualizarAerolineaNoEncontrada() {
        // Arrange
        Long id = 1L;
        AerolineaDto nuevaAerolinea = new Aerolinea( );

        when(aerolineaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = this.aerolineaService.actualizarAerolinea(id, nuevaAerolinea);

        // Assert
        assertFalse(resultado);
    }
    @Test
    public void testEliminarAerolineaExistente() {
        // Arrange
        Long idAerolineaExistente = 1L;
        when(aerolineaRepository.findById(idAerolineaExistente)).thenReturn(Optional.of(new Aerolinea()));

        // Act
        boolean resultado = aerolineaService.eliminarAerolinea(idAerolineaExistente);

        // Assert
        assertTrue(resultado);
        verify(aerolineaRepository).deleteById(idAerolineaExistente);
    }
    @Test
    public void testEliminarAerolineaInexistente() {
        // Arrange
        Long idAerolineaInexistente = 2L;
        when(aerolineaRepository.findById(idAerolineaInexistente)).thenReturn(Optional.empty());

        // Act
        boolean resultado = aerolineaService.eliminarAerolinea(idAerolineaInexistente);

        // Assert
        assertFalse(resultado);
        verify(aerolineaRepository, never()).deleteById(idAerolineaInexistente);
    }
    @Test
    public void testEliminarAerolineaConIdNulo() {
        // Arrange
        Long idAerolineaNulo = null;

        // Act
        boolean resultado = aerolineaService.eliminarAerolinea(idAerolineaNulo);

        // Assert
        assertFalse(resultado);
        verify(aerolineaRepository, never()).deleteById(idAerolineaNulo);
    }
    @Test
    public void testEliminarAerolineaConIdNegativo() {
        // Arrange
        Long idAerolineaNegativo = -1L;

        // Act
        boolean resultado = aerolineaService.eliminarAerolinea(idAerolineaNegativo);

        // Assert
        assertFalse(resultado);
        verify(aerolineaRepository, never()).deleteById(idAerolineaNegativo);
    }
    @Test
    public void testObtenerAerolineasPorCiudadExitoso() {
        // Arrange
        String ciudad = "Estados Unidos";
        List<Aerolinea> aerolineasEsperadas = Arrays.asList(
                new Aerolinea("American Airlines"),
                new Aerolinea("Delta Airlines")
        );

        when(aerolineaRepository.findByCiudad(ciudad)).thenReturn(aerolineasEsperadas);

        // Act
        List<Aerolinea> aerolineasObtenidas = this.aerolineaService.obtenerAerolineasPorCiudad(ciudad);

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).hasSize(aerolineasEsperadas.size());
        assertThat(aerolineasObtenidas).containsExactlyInAnyOrderElementsOf(aerolineasEsperadas);
    }
    @Test
    public void testObtenerAerolineasPorCiudadInexistente() {
        // Arrange
        String ciudadInexistente = "Ciudad Inexistente";

        when(aerolineaRepository.findByCiudad(ciudadInexistente)).thenReturn(Collections.emptyList());

        // Act
        List<Aerolinea> aerolineasObtenidas = aerolineaService.obtenerAerolineasPorCiudad(ciudadInexistente);

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).isEmpty();
    }

    @Test
    public void obtenerAerolineasPorPaisYCiudadDeberiaRetornarAerolineasDelPaisYCiudadEspecificados() {
        // Arrange
        List<Aerolinea> aerolineas = Arrays.asList(
                new Aerolinea("Delta Airlines", "Estados Unidos", "Nueva York"),
                new Aerolinea("British Airways", "Reino Unido", "Londres"),
                new Aerolinea("Air France", "Francia", "París"),
                new Aerolinea("Lufthansa", "Alemania", "Berlín")
        );

        // Simula que el repositorio devuelve todas las aerolíneas
        when(aerolineaRepository.findAll()).thenReturn(aerolineas);

        // Act
        List<Aerolinea> aerolineasEstadosUnidosNuevaYork = this.aerolineaService.obtenerAerolineasPorPaisYCiudad("Estados Unidos", "Nueva York");

        // Assert
        assertThat (aerolineasEstadosUnidosNuevaYork).isNotNull ( );
        assertThat(aerolineasEstadosUnidosNuevaYork).hasSize(1);
        assertThat(aerolineasEstadosUnidosNuevaYork.get(0).getNombre()).isEqualTo("Delta Airlines");
    }

    @Test
    public void testObtenerAerolineasPorPaisYCiudad() {
        // Arrange
        String pais = "Estados Unidos";
        String ciudad = "Nueva York";

        List<Aerolinea> aerolineasEsperadas = Arrays.asList(
                new Aerolinea("Delta Airlines", pais, ciudad),
                new Aerolinea("American Airlines", pais, ciudad),
                new Aerolinea("United Airlines", pais, ciudad)
        );

        // Simula que el repositorio devuelve aerolíneas para el país y la ciudad especificados
        when(aerolineaRepository.findByPaisAndCiudad(pais, ciudad)).thenReturn(aerolineasEsperadas);

        // Act
        List<Aerolinea> aerolineasObtenidas = this.aerolineaService.obtenerAerolineasPorPaisYCiudad(pais, ciudad);

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).hasSize(aerolineasEsperadas.size());
        assertThat(aerolineasObtenidas).containsExactlyInAnyOrderElementsOf(aerolineasEsperadas);
    }
    @Test
    public void testObtenerAerolineasPorPaisYCiudadExitoso() {
        // Arrange
        String pais = "Estados Unidos";
        String ciudad = "Nueva York";
        List<Aerolinea> aerolineasEsperadas = Arrays.asList(
                new Aerolinea(1L, "Delta Airlines", pais, ciudad),
                new Aerolinea(2L, "American Airlines", pais, ciudad)
        );

        // Simula que el repositorio devuelve las aerolíneas para el país y ciudad especificados
        when(aerolineaRepository.findByPaisAndCiudad(pais, ciudad)).thenReturn(aerolineasEsperadas);

        // Act
        List<Aerolinea> aerolineasObtenidas = aerolineaService.obtenerAerolineasPorPaisYCiudad(pais, ciudad);

        // Assert
        assertThat(aerolineasObtenidas).isNotNull();
        assertThat(aerolineasObtenidas).hasSize(2);
        assertThat(aerolineasObtenidas).containsExactlyInAnyOrderElementsOf(aerolineasEsperadas);
    }

    @Test
    public void testObtenerAerolineasPorPaisYCiudadConPaisNulo() {
        // Arrange
        String ciudad = "Barcelona";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.obtenerAerolineasPorPaisYCiudad(null, ciudad));
    }

    @Test
    public void testObtenerAerolineasPorPaisYCiudadConCiudadNula() {
        // Arrange
        String pais = "España";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.obtenerAerolineasPorPaisYCiudad(pais, null));
    }

    @Test
    public void testObtenerAerolineasPorPaisYCiudadConPaisVacio() {
        // Arrange
        String ciudad = "París";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.obtenerAerolineasPorPaisYCiudad("", ciudad));
    }

    @Test
    public void testObtenerAerolineasPorPaisYCiudadConCiudadVacia() {
        // Arrange
        String pais = "Francia";

        // Assert
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.obtenerAerolineasPorPaisYCiudad(pais, ""));
    }


}






