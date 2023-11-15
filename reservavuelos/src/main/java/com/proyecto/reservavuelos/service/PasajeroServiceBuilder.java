package com.proyecto.reservavuelos.service;

import com.proyecto.reservavuelos.repository.PasajeroRepository;

public class PasajeroServiceBuilder {
    private PasajeroRepository pasajeroRepository;

    public PasajeroServiceBuilder setPasajeroRepository(PasajeroRepository pasajeroRepository) {
        this.pasajeroRepository = pasajeroRepository;
        return this;
    }

    public PasajeroService createPasajeroService() {
        return new PasajeroService (pasajeroRepository);
    }
}