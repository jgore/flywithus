package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;

import java.math.BigDecimal;

public interface PriceService {
    BigDecimal calculateReservation(UserDto userDto, FlightDto flightDto, ReservationDto reservationDto);
}
