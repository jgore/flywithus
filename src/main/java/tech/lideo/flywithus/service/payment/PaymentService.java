package tech.lideo.flywithus.service.payment;

import tech.lideo.flywithus.controller.dto.CreditCardDetailsDto;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse creditCardPayment(CreditCardDetailsDto creditCardDetailsDto, UUID sercretCode);
}
