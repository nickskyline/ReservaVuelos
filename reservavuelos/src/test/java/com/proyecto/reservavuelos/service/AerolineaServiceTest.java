package com.proyecto.reservavuelos.service;
import com.proyecto.reservavuelos.dto.AerolineaDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.repository.AerolineaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
//@SpringBootTest

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
            aerolineaRepository = mock(AerolineaRepository.class);
            aerolineaService = new AerolineaService(aerolineaRepository);
            this.aerolineaRepository = mock(AerolineaRepository.class);// Mock del repositorio
            MockitoAnnotations.openMocks(this);
         this.aerolineaService= new   AerolineaService(aerolineaRepository); // Inicializa el servicio con el mock del repositorio
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

    @Test
    void crearAerolineaDeberiaLanzarExcepcionCuandoNombreEsInvalido() {
        AerolineaDto aerolinea = new AerolineaDto("Aerolínea 1", "Francia","4553453");
        aerolinea.setNombre(null); // O setNombre("") para el caso vacío

        // Verifica que se lance la excepción cuando el nombre no es válido
        assertThrows(IllegalArgumentException.class, () -> aerolineaService.crearAerolinea((AerolineaDto) aerolinea));
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

   /* @Test
    public void testCrearNuevaAerolinea() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea Nueva", "Francia", "4444224");

        // Mock de aerolineaRepository para simular que la aerolínea no existe en la base de datos
        when(aerolineaRepository.existsByNombre(aerolineaDto.getNombre())).thenReturn(false);

        // Llamada al método a probar
        Aerolinea nuevaAerolinea = this.aerolineaService.crearAerolinea(aerolineaDto);

        // Verificaciones
        assertEquals("Aerolínea Nueva", nuevaAerolinea.getNombre());
        assertEquals("Francia", nuevaAerolinea.getPais());
        assertEquals("4444224", nuevaAerolinea.getTelefono());

        // Verificar que el método existsByNombre se llamó una vez con el nombre correcto
        verify(aerolineaRepository, times(1)).existsByNombre("Aerolínea Nueva");

        // Verificar que el método save se llamó una vez
        verify(aerolineaRepository, times(1)).save(any(Aerolinea.class));
    }*/

   /*@Test
    public void testGuardarAerolineaEnBaseDeDatos() {
        // Datos de ejemplo para la prueba
        AerolineaDto aerolineaDto = new AerolineaDto("Aerolínea Nueva", "Francia", "4444224");

       //Mock de aerolineaRepository para simular que la aerolínea no existe en la base de datos */
       /* when(aerolineaRepository.existsByNombre(aerolineaDto.getNombre())).thenReturn(false);

        // Llamada al método a probar
        Aerolinea nuevaAerolinea = this.aerolineaService.crearAerolinea(aerolineaDto);

        // Verificar que el método save se llamó una vez con la aerolínea correcta
        verify(aerolineaRepository, times(1)).save(nuevaAerolinea);
    }*/
   @Test
   public void testObtenerTodasLasAerolineas() {
       // Datos de ejemplo para la prueba
       List<Aerolinea> aerolineas = new ArrayList<>();
       aerolineas.add(new Aerolinea("Aerolínea 1", "Francia", "4444224"));
       aerolineas.add(new Aerolinea("Aerolínea 2", "España", "5555333"));

       // Mock de aerolineaRepository para simular el comportamiento de findAll
       when(aerolineaRepository.findAll()).thenReturn(aerolineas);

       // Llamada al método a probar
       List<Aerolinea> result = this.aerolineaService.obtenerTodasLasAerolineas();

       // Verificar que el método findAll se llamó una vez
       verify(aerolineaRepository, times(1)).findAll();

       // Verificar que el resultado de la prueba es igual a la lista de aerolíneas simulada
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
        }


























