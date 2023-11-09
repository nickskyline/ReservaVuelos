package com.proyecto.reservavuelos.controller;
import com.proyecto.reservavuelos.dto.AerolineaDto;
import com.proyecto.reservavuelos.model.Aerolinea;
import com.proyecto.reservavuelos.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/aerolineas")
public class AerolineaController {
    private AerolineaService aerolineaService;

    @Autowired
    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }

    @PostMapping
    public ResponseEntity<String> crearAerolinea(@RequestBody AerolineaDto aerolinea) {
        Aerolinea nuevaAerolinea = aerolineaService.crearAerolinea(aerolinea);
        return new ResponseEntity<>("Aerolínea creada con éxito", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aerolinea>> obtenerTodasLasAerolineas() {
        List<Aerolinea> aerolineas = aerolineaService.obtenerTodasLasAerolineas();
        return new ResponseEntity<>(aerolineas, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Aerolinea> obtenerAerolineaPorId(@PathVariable Long id) {
        Aerolinea aerolinea = aerolineaService.obtenerAerolineaPorId(id);

        if (aerolinea != null) {
            return new ResponseEntity<>(aerolinea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarAerolinea(@PathVariable Long id, @RequestBody Aerolinea nuevaAerolinea) {
        boolean actualizada = aerolineaService.actualizarAerolinea(String.valueOf (id), nuevaAerolinea);

        if (actualizada) {
            return new ResponseEntity<>("Aerolínea actualizada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aerolínea no encontrada", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAerolinea(@PathVariable Long id) {
        boolean eliminada = aerolineaService.eliminarAerolinea(id);

        if (eliminada) {
            return new ResponseEntity<>("Aerolínea eliminada con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Aerolínea no encontrada", HttpStatus.NOT_FOUND);
        }
    }



}