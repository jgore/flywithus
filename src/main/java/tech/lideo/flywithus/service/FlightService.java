package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.FlightDto;

public interface FlightService {

    FlightDto create (FlightDto flightDto);
    FlightDto get ( Long id );
}
