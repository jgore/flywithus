package tech.lideo.flywithus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.lideo.flywithus.controller.dto.*;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.ReservationRepository;
import tech.lideo.flywithus.service.payment.PaymentResponse;
import tech.lideo.flywithus.service.payment.PaymentService;
import tech.lideo.flywithus.service.payment.PaymentStatus;
import tech.lideo.flywithus.service.price.PriceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;

    private UserService userService;

    private FlightRepository flightRepository;

    private PriceService priceService;

    private PaymentService paymentService;

    private String autoCancelDays;

    private String cancelDays;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserService userService,
                                  FlightRepository flightRepository,
                                  PriceService priceService,
                                  PaymentService paymentService,
                                  @Value("${flywithus.autoCancel.days}") String autoCancelDays,
                                  @Value("${flywithus.cancel.days}") String cancelDays) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.flightRepository = flightRepository;
        this.priceService = priceService;
        this.paymentService = paymentService;
        this.autoCancelDays = autoCancelDays;
        this.cancelDays = cancelDays;
    }

    @Override
    public ReservationDto create(ReservationDto reservationDto) {

        String userEmail = reservationDto.getUserEmail();
        UserDto userByEmail = userService.getByEmail(userEmail);

        if (userByEmail == null) {
            throw new IllegalArgumentException("user with given email does not exist : email =" + userEmail);
        }

        Long flightId = reservationDto.getFlightId();
        FlightDto flightDto = flightRepository.get(flightId);

        if (flightDto == null) {
            throw new IllegalArgumentException("flight with given id does not exist : id =" + flightId);
        }

        BigDecimal price = priceService.calculateReservation(userByEmail, flightDto, reservationDto);
        reservationDto.setPrice(price);
        reservationDto.setReservationSecretCode(UUID.randomUUID());

        reservationRepository.create(reservationDto);

        return reservationDto;
    }


    @Override
    public List<ReservationDto> getByEmail(String email) {
        return reservationRepository.getByEmail(email);
    }

    @Override
    public ReservationDto cancel(UUID reservationSecretCode) {

        ReservationDto res = reservationRepository.getBySecretCode(reservationSecretCode);

        if (res == null) {
            throw new IllegalArgumentException(" secret code is incorrect - reservation does not exist");
        }

        FlightDto flightDto = flightRepository.get(res.getFlightId());
        LocalDate departureDate = flightDto.getDepartureDate();

        if (departureDate.isAfter(LocalDate.now().minusDays(Long.valueOf(cancelDays)))) {
            throw new IllegalArgumentException(" Reservation can be CANCELLED only " + cancelDays + " days before departure");
        }

        res.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.updateStatus(res);
    }

    @Override
    public PaymentResponse pay(CreditCardDetailsDto creditCardDetailsDto, UUID reservationSecretCode) {
        ReservationDto resBySecretCode = reservationRepository.getBySecretCode(reservationSecretCode);
        if (resBySecretCode == null) {
            throw new IllegalArgumentException("secret code is incorrect - there is no reservation");
        }

        PaymentResponse paymentResponse = paymentService.creditCardPayment(creditCardDetailsDto, reservationSecretCode);

        if (PaymentStatus.APPROVED.equals(paymentResponse.getPaymentStatus())) {
            resBySecretCode.setStatus(ReservationStatus.CONFIRMED);
            reservationRepository.updateStatus(resBySecretCode);
        }

        return paymentResponse;

    }

    @Override
    public boolean autoCancelExpiredReservations() {
        List<ReservationDto> reservations = reservationRepository.getByStatus(ReservationStatus.CREATED);
        LocalDate now = LocalDate.now();

        reservations.forEach(reservationDto -> {
            LocalDate created = reservationDto.getCreated();

            if (now.minusDays(Long.valueOf(autoCancelDays)).isAfter(created)) {
                reservationDto.setStatus(ReservationStatus.AUTO_CANCELLED);
                reservationRepository.updateStatus(reservationDto);
            }
        });
        return true;
    }
}
