package tech.lideo.flywithus.repository;

import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlywithusApplication;
import tech.lideo.flywithus.controller.dto.FlightDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private final List<FlightDto> flights = new ArrayList<>();

    private  Long id = 0L;

    @Override
    public List<FlightDto> getAll() {
        return flights.stream()
                .map(this::copyFlightDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto get(Long id) {
        return flights.stream().
                filter(dto -> id.equals(dto.getId())).
                findFirst().orElse(null);
    }

    @Override
    public FlightDto create(FlightDto flightDto) {

        if (flightDto.getId() != null) {
            throw new IllegalArgumentException(" id need to be null for new flights");
        }
        FlightDto copy = copyFlightDto(flightDto);
        copy.setId(id++);
        flights.add(copy);

        return flightDto;
    }

    @Override
    public void deleteAll() {
        flights.clear();
    }

    @Override
    public int getCount() {
        return flights.size();
    }

    private FlightDto copyFlightDto(FlightDto flightDto) {
        String jsonFlightDto = FlywithusApplication.gson.toJson(flightDto);
        return FlywithusApplication.gson.fromJson(jsonFlightDto, FlightDto.class);
    }
}
