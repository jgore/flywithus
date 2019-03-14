package tech.lideo.flywithus.repository;

import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.ReservationStatus;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository {

    List<ReservationDto> getAll();
    List<ReservationDto> getByEmail(String email);
    List<ReservationDto> getByStatus(ReservationStatus status);
    ReservationDto getBySecretCode(UUID secretCode);
    ReservationDto create (ReservationDto reservationDto);

    void deleteAll();
    int getCount();

    ReservationDto get(Long id);
    ReservationDto updateStatus(ReservationDto reservationDto);
}
