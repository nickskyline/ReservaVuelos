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
import com.proyecto.reservavuelos.dto.AerolineaDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
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
    //-----------------------------------*****************----------------------------//

/*1.-testCrearNuevaAerolinea*/
    @Test
    public void testCrearNuevaAerolineaConDto() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea Nueva", "Francia", "4444224");

        // Crea una instancia de Aerolinea basada en el DTO
        Aerolinea nuevaAerolinea = new Aerolinea();
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
        Aerolinea aerolinea = new Aerolinea();
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
    @Test
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
    }

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

    @Test
    public void testObtenerTodasLasAerolineas() {
        // Datos de ejemplo para la prueba
        List<Aerolinea> aerolineas = new ArrayList<>();
        aerolineas.add(new Aerolinea("Aerolínea 1", "Francia", "4444224"));
        aerolineas.add(new Aerolinea("Aerolínea 2", "España", "5555333"));

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
        aerolineas.add(new Aerolinea("Aerolínea 1", "Francia", "4444224"));
        aerolineas.add(new Aerolinea("Aerolínea 2", "España", "5555333"));

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
    }
    @Test
    public void testObtenerAerolineaPorIdExistente() {
        // ID de la aerolínea que se busca
        Long aerolineaId = 1L;

        // Aerolínea simulada que se espera encontrar en la base de datos
        Aerolinea aerolineaSimulada = new Aerolinea("Aerolínea 1", "Francia", "4444224");
        when(aerolineaRepository.findById(aerolineaId)).thenReturn(Optional.of(aerolineaSimulada));

        // Llamada al método a probar
        Aerolinea result = aerolineaService.obtenerAerolineaPorId(aerolineaId);

        // Verificar que el método findById se llamó una vez con el ID esperado
        verify(aerolineaRepository, times(1)).findById(aerolineaId);

        // Verificar que el resultado es igual a la aerolínea simulada
        assertEquals(aerolineaSimulada, result);
    }
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

    @Test
    public void testActualizarAerolineaInexistente() {
        // ID de una aerolínea inexistente
        Long aerolineaId = 2L;

        // Nueva aerolínea con datos actualizados
        Aerolinea nuevaAerolinea = new Aerolinea();
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
        Aerolinea aerolineaExistente = new Aerolinea();
        aerolineaExistente.setId(String.valueOf (id));
        aerolineaExistente.setNombre("Aerolínea Antigua");
        aerolineaExistente.setPais("España");

        Aerolinea nuevaAerolinea = new Aerolinea();
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
        Aerolinea nuevaAerolinea = new Aerolinea();

        when(aerolineaRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = this.aerolineaService.actualizarAerolinea(id, nuevaAerolinea);

        // Assert
        assertFalse(resultado);
    }



   /* @Test
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
   /* @Test
    public void obtenerTodasLasAerolineasDeberiaRetornarListaVaciaSiNoHayAerolineas() {
        // Arrange
        when(aerolineaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Aerolinea> aerolineas = aerolineaService.obtenerTodasLasAerolineas();

        // Assert
        assertThat(aerolineas).isNotNull();
        assertThat(aerolineas).isEmpty();
    }
   /* @Test
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

   /* @Test
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
    }*/

}






