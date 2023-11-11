package com.proyecto.reservavuelos.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.repository.PasajeroRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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



}

/*2.-test convertirPasajeroDtoAEntidad*/





    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/
    /*1.-testCrearNuevoPasajero*/





