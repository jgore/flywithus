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

        String userLogin = reservationDto.getUserLogin();
        UserDto userByLogin = userService.getByLogin(userLogin);

        if (userByLogin == null) {
            throw new IllegalArgumentException("user with given login does not exist : login =" + userLogin);
        }

        Long flightId = reservationDto.getFlightId();
        FlightDto flightDto = flightRepository.get(flightId);

        if (flightDto == null) {
            throw new IllegalArgumentException("flight with given id does not exist : id =" + flightId);
        }

        BigDecimal price = priceService.calculateReservation(userByLogin, flightDto, reservationDto);
        reservationDto.setPrice(price);

        return reservationRepository.create(reservationDto);

    }

    @Override
    public List<ReservationDto> getByLogin(String login) {
        return reservationRepository.getByLogin(login);
    }
}
