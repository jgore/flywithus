package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationDto create (ReservationDto reservationDto);
    List<ReservationDto> getByEmail(String email );

    ReservationDto cancel(UUID reservationSecretCode);

    boolean autoCancelExpiredReservations();

}
