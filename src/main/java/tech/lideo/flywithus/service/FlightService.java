package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto create (FlightDto flightDto);
    List<FlightDto> getAll ( );
}
