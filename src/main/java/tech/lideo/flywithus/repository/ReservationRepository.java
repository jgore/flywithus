package tech.lideo.flywithus.repository;

import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.util.List;

public interface ReservationRepository {

    List<ReservationDto> getByEmail(String email);
    ReservationDto create (ReservationDto reservationDto);

    void deleteAll();
    int getCount();
}
