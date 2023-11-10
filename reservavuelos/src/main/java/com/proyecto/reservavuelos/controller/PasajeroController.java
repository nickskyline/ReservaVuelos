package com.proyecto.reservavuelos.controller;
import com.proyecto.reservavuelos.dto.PasajeroDto;
import com.proyecto.reservavuelos.model.Pasajero;
import com.proyecto.reservavuelos.service.PasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pasajeros")
public class PasajeroController {

    private PasajeroService pasajeroService;


    @Autowired

    public PasajeroController(PasajeroService pasajeroService) {
        this.pasajeroService = pasajeroService;
    }

    @PostMapping
    public ResponseEntity<Pasajero> crearPasajero(@RequestBody PasajeroDto pasajero) {
        Pasajero nuevopasajero;
        Pasajero nuevoPasajero = pasajeroService.crearPasajero(pasajero);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPasajero);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pasajero> obtenerPasajeroPorId(@PathVariable Long id){
        Pasajero pasajero = pasajeroService.obtenerPasajeroPorId (id);
        if (pasajero != null) {
            return ResponseEntity.ok(pasajero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Pasajero>> obtenerTodosLosPasajeros() {
        List<Pasajero> pasajeros = pasajeroService.obtenerTodosLosPasajeros();
        return ResponseEntity.ok(pasajeros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pasajero> actualizarPasajero(@PathVariable Long id, @RequestBody Pasajero pasajeroActualizado) {
        Pasajero pasajero = pasajeroService.actualizarPasajero(id, (PasajeroDto) pasajeroActualizado);
        if (pasajero != null) {
            return ResponseEntity.ok(pasajero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPasajero(@PathVariable Long id) {
        if (pasajeroService.eliminarPasajero(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
