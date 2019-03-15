package tech.lideo.flywithus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.FlightDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<FlightDto> flights = new ArrayList<>();

    private Long id = 0L;

    @Override
    public List<FlightDto> getAll() {
        return flights.stream()
                .map(this::copyFlightDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto get(Long id) {
        return flights.stream().
                filter(dto -> id.equals(dto.getId()))
                .map(this::copyFlightDto)
                .findFirst()
                .orElse(null);

    }

    @Override
    public FlightDto create(FlightDto flightDto) {

        if (flightDto.getId() != null) {
            throw new IllegalArgumentException(" id need to be null for new flights");
        }

        if( flightDto.getPrice() == null)
        {
            throw new IllegalArgumentException("price cannot be null");
        }
        FlightDto copy = copyFlightDto(flightDto);
        copy.setId(id++);
        flights.add(copy);

        logger.info("created flight with id =" + copy.getId());

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
        String jsonFlightDto = FlyWithUsApplication.gson.toJson(flightDto);
        return FlyWithUsApplication.gson.fromJson(jsonFlightDto, FlightDto.class);
    }
}
