package com.proyecto.reservavuelos.controller;


import com.proyecto.reservavuelos.dto.CiudadDto;
import com.proyecto.reservavuelos.model.Ciudad;
import com.proyecto.reservavuelos.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ciudades")
public class CiudadController {

    //http://localhost:8080/api/v1/ciudades:
    private CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @PostMapping
    public Ciudad crearCiudad(@RequestBody Ciudad ciudad) {
        return ciudadService.agregarCiudad(ciudad);
    }

    @GetMapping
    public List<Ciudad> listarCiudades() {
        return ciudadService.listarCiudades();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCiudadPorId(@PathVariable Long id, @RequestBody Ciudad nuevaCiudad) {

        boolean actualizada = ciudadService.actualizarCiudadPorId(id, nuevaCiudad);

        if (actualizada) {
            return new ResponseEntity<>("Ciudad actualizada exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ciudad no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCiudadPorId(@PathVariable Long id) {

        boolean eliminada = ciudadService.eliminarCiudadPorId(id);

        if (eliminada) {
            return new ResponseEntity<>("Ciudad eliminada exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ciudad no encontrada", HttpStatus.NOT_FOUND);
        }
    }

}