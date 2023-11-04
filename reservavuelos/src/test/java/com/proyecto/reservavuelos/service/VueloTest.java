package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.repository.VueloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VueloTest {

    private VueloService vueloService;
    private VueloRepository vueloRepository;

    @BeforeEach
    public void setUp() {
         vueloService = new VueloService(vueloRepository);
    }


    @Test
    public void crearVueloTest() {

    }

    @Test
    public void crearVueloFailTest() {

    }

}
