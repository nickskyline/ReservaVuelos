package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.dto.PersonaDto;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.repository.PersonaRepository;
import com.proyecto.reservavuelos.util.Genero;
import com.proyecto.reservavuelos.util.TipoDocumento;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PersonaServiceTest {

    @InjectMocks
    private PersonaService personaService;

    @Mock
    private PersonaRepository mockPersonaRepository;

    @BeforeEach
    void setUp() {
        mockPersonaRepository = Mockito.mock(PersonaRepository.class);
        personaService = new PersonaService(mockPersonaRepository);
    }

    public PersonaDto personaValida() {
        PersonaDto personaDto = new PersonaDto();

        personaDto.setNombres("Pepe Eduardo");
        personaDto.setApellidos("Solarte Jimenez");
        personaDto.setGenero(Genero.MASCULINO);
        personaDto.setTipoDocumento(TipoDocumento.CEDULA_DE_CIUDADANIA);
        personaDto.setNumeroDocumento("1234567890");
        personaDto.setFechaNacimiento("1999-12-04");
        personaDto.setPaisOrigen("Colombia");
        personaDto.setPaisResidencia("Colombia");
        personaDto.setEmail("pepesolarte23@ejemplo.com");
        personaDto.setTelefono("1234569703");

        return personaDto;
    }

    @Test
    public void crearPersonaTest() {
        //Arrange
        PersonaDto personaDto = personaValida();

        //Act
        Persona nuevaPersona = personaService.crearPersona(personaDto);

        //Assert
        verify(mockPersonaRepository, times(1)).save(any());
    }

    @Test
    public void crearPersonaSinNombres() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setNombres(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaSinApellidos() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setApellidos(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaSinGenero() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setGenero(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }


    @Test
    public void crearPersonaSinTipoDocumento() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setTipoDocumento(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaSinNumeroDocumento() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setNumeroDocumento(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaConDocumentoInvalido() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setNumeroDocumento("hola");

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaConNumeroDocumentoNegativo() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setNumeroDocumento("-1235129");

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }



    @Test
    public void crearPersonaSinFechaNacimiento() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setFechaNacimiento(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaConFechaDeNacimientoEnElFuturo() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setFechaNacimiento("2024-05-24");

        //Assert
        assertThrows(IllegalArgumentException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaConFechaDeNacimientoEnElPasado() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setFechaNacimiento("1899-05-24");

        //Assert
        assertThrows(IllegalArgumentException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaSinPaisOrigen() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setPaisOrigen(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaSinPaisResidencia() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setPaisResidencia(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }


    @Test
    public void crearPersonaSinEmail() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setEmail(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaComEmailInvalido() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setEmail("emailtotalmenteinvalido.com");

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }


    @Test
    public void crearPersonaSinTelefono() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setTelefono(null);

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }

    @Test
    public void crearPersonaConTelefonoInvalido() {

        //Arramge
        PersonaDto personaDto = personaValida();

        //Act
        personaDto.setTelefono("hjfasfasjhf");

        //Assert
        assertThrows(ValidationException.class, () -> personaService.crearPersona(personaDto));
    }



}