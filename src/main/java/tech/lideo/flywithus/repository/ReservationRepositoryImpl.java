package tech.lideo.flywithus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final List<ReservationDto> reservations = new ArrayList<>();

    private Long id = 0L;

    @Override
    public List<ReservationDto> getAll() {
        return reservations.stream()
                .map(this::copyReservation)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getByEmail(String email) {

        return reservations.stream()
                .filter(dto -> email.equals(dto.getUserEmail()))
                .map(this::copyReservation)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto create(ReservationDto reservationDto) {
        reservationDto.setId(id++);
        reservations.add(copyReservation(reservationDto));

        logger.info("created reservation for user with email :" + reservationDto.getUserEmail());

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

    @Override
    public ReservationDto get(Long id) {
        return reservations.stream()
                .filter(dto -> id.equals(dto.getId()))
                .map(this::copyReservation)
                .findAny().orElse(null);
    }

    @Override
    public ReservationDto updateStatus(ReservationDto reservationDto) {

        ReservationDto persistedReservation =  reservations.stream()
                .filter(dto -> reservationDto.getId().equals(dto.getId()))
                .findAny()
                .orElse(null);

        if( persistedReservation == null){
            String msg = "updateStatus reservation failed, reservation does not exist";
            logger.error(msg);
            throw new IllegalArgumentException(msg);
        }

        persistedReservation.setStatus(reservationDto.getStatus());
        logger.info("changed status : id "+reservationDto.getId()+" status = "+persistedReservation.getStatus());

        return reservationDto;
    }

    private ReservationDto copyReservation(ReservationDto reservationDto) {
        String jsonFlightDto = FlyWithUsApplication.gson.toJson(reservationDto);
        return FlyWithUsApplication.gson.fromJson(jsonFlightDto, ReservationDto.class);
    }
}
