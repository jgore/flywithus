package tech.lideo.flywithus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.ReservationRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PriceService priceService;

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
}
