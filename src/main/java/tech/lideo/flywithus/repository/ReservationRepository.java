package tech.lideo.flywithus.repository;

import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    List<ReservationDto> getAll();
    List<ReservationDto> getByEmail(String email);
    ReservationDto getBySecretCode(UUID secretCode);
    ReservationDto create (ReservationDto reservationDto);

    void deleteAll();
    int getCount();

    ReservationDto get(Long id);
    ReservationDto updateStatus(ReservationDto reservationDto);
}
