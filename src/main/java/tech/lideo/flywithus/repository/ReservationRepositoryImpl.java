package tech.lideo.flywithus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<ReservationDto> reservations = new ArrayList<>();

    @Override
    public List<ReservationDto> getByLogin(String login) {

        return reservations.stream()
                .filter(dto -> login.equals(dto.getUserLogin()))
                .map(this::copyReservation)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto create(ReservationDto reservationDto) {
        reservations.add(copyReservation(reservationDto));

        logger.info("created reservation for user with login :" + reservationDto.getUserLogin());

        return reservationDto;
    }

    @Override
    public void deleteAll() {
        reservations.clear();
    }

    @Override
    public int getCount() {
        return reservations.size();
    }

    private ReservationDto copyReservation(ReservationDto reservationDto) {
        String jsonFlightDto = FlyWithUsApplication.gson.toJson(reservationDto);
        return FlyWithUsApplication.gson.fromJson(jsonFlightDto, ReservationDto.class);
    }
}
