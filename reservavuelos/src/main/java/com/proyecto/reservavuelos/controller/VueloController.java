package com.proyecto.reservavuelos.controller;

import com.proyecto.reservavuelos.dto.VueloDto;
import com.proyecto.reservavuelos.model.Vuelo;
import com.proyecto.reservavuelos.service.VueloService;
import com.proyecto.reservavuelos.util.TipoVuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/vuelos")
public class VueloController {

    private VueloService vueloService;

    @Autowired
    public VueloController(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @PostMapping
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody VueloDto vueloDto) {

        Vuelo nuevoVuelo = vueloService.crearVuelo(vueloDto);
        return new ResponseEntity<>(nuevoVuelo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> obtenerVuelo(@PathVariable Long id) {
        Vuelo vuelo = vueloService.obtenerVueloPorId(id);
        if (vuelo != null) {
            return new ResponseEntity<>(vuelo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vuelo>> obtenerTodosLosVuelos() {
        List<Vuelo> vuelos = vueloService.obtenerTodosLosVuelos();
        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVuelo(@PathVariable Long id, @RequestBody VueloDto vueloDtoActualizado) {
        vueloService.actualizarVuelo(id, vueloDtoActualizado);
        if (vueloDtoActualizado != null) {
            return new ResponseEntity<>(vueloDtoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable Long id) {
        boolean eliminado = vueloService.eliminarVuelo(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
