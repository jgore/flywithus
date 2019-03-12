package tech.lideo.flywithus.repository;

import tech.lideo.flywithus.controller.dto.FlightDto;

import java.util.List;

public interface FlightRepository {

    List<FlightDto> getAll();
    FlightDto get(Long id);
    FlightDto create (FlightDto flightDto);

    void deleteAll();
    int getCount();
}
