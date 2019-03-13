package tech.lideo.flywithus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public FlightDto create(FlightDto flightDto) {
        return flightRepository.create(flightDto);
    }

    @Override
    public FlightDto get(Long id) {
        return flightRepository.get(id);
    }
}
