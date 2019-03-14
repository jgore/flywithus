package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.CreditCardDetailsDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.service.payment.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    ReservationDto create (ReservationDto reservationDto);
    List<ReservationDto> getByEmail(String email );

    ReservationDto cancel(UUID reservationSecretCode);
    PaymentResponse pay(CreditCardDetailsDto creditCardDetailsDto, UUID reservationSecretCode);

    boolean autoCancelExpiredReservations();

}
