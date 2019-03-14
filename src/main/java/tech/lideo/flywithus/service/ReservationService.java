package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.util.List;

public interface ReservationService {

    ReservationDto create (ReservationDto reservationDto);
    List<ReservationDto> getByEmail(String email );

    boolean autoCancelExpiredReservations();

}
