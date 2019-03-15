package tech.lideo.flywithus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.CreditCardDetailsDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.service.ReservationService;
import tech.lideo.flywithus.service.payment.PaymentResponse;
import tech.lideo.flywithus.service.payment.PaymentStatus;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST)
    public ReservationDto create(@Valid @RequestBody ReservationDto reservationDto) {
        return reservationService.create(reservationDto);
    }

    @RequestMapping(value = "/cancel/{reservationSecretCode}", method = RequestMethod.POST)
    public ReservationDto cancel(@PathVariable("reservationSecretCode") String reservationSecretCode) {

        UUID uuid = UUID.fromString(reservationSecretCode);

        return reservationService.cancel(uuid);
    }

    @RequestMapping(value = "/pay/{reservationSecretCode}", method = RequestMethod.POST)
    public PaymentResponse pay(@PathVariable("reservationSecretCode") String reservationSecretCode,
                               @RequestBody CreditCardDetailsDto creditCardDetailsdto) {

        UUID secretCode = UUID.fromString(reservationSecretCode);
        return reservationService.pay(creditCardDetailsdto, secretCode);

    }
}
