package com.proyecto.reservavuelos.controller;

import com.proyecto.reservavuelos.dto.PersonaDto;
import com.proyecto.reservavuelos.model.Persona;
import com.proyecto.reservavuelos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/personas")
public class PersonaController {
    private PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<String> crearPersona(@RequestBody PersonaDto persona) {
        Persona nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>("Persona creada con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
        List<Persona> personas = personaService.obtenerTodasLasPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/cedula")
    public ResponseEntity<Persona> buscarPorNumeroDocumento(@RequestParam("numeroDocumento") String numeroDocumento) {
        Persona persona = personaService.buscarPorNumeroDocumento(numeroDocumento);
        if (persona != null) {
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDto nuevaPersona) {
        boolean actualizada = personaService.actualizarPersona(id, nuevaPersona);

        if (actualizada) {
            return new ResponseEntity<>("Persona actualizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Persona no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable Long id) {
        boolean eliminada = personaService.eliminarPersona(id);

        if (eliminada) {
            return new ResponseEntity<>("Persona eliminada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Persona no encontrada", HttpStatus.NOT_FOUND);
        }
    }


}
