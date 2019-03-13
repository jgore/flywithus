package tech.lideo.flywithus;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.UserRepository;
import tech.lideo.flywithus.service.ReservationService;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class FlyWithUsApplication implements CommandLineRunner {

    public static final Gson gson = new Gson();

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationService reservationService;

    public static void main(String[] args) {
        SpringApplication.run(FlyWithUsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100; i++) {
            flightRepository.create(prepareFlight());
        }

        for (int i = 0; i < 100; i++) {
            UserDto userDto = prepareUserDto();
            userDto.setLogin(userDto.getLogin() + i);

            userRepository.create(userDto);
        }

        for (long j = 0; j < 10; j++) {

            FlightDto flightDto = flightRepository.get(j);
            UserDto userDto = userRepository.get("test" + j);

            for (long k= 0; k < 10; k++) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setUserLogin(userDto.getLogin());
                reservationDto.setFlightId(flightDto.getId());
                reservationService.create(reservationDto);
            }

        }
    }

    //@FIXME refactor - copy paste to some common class
    private FlightDto prepareFlight() {
        FlightDto flightDto = new FlightDto();
        flightDto.setArrivalCity("wroclaw");
        flightDto.setArrivalDate(new Date());
        flightDto.setDepartureCity("London");
        flightDto.setDepartureDate(new Date());

        flightDto.setPrice(new BigDecimal(15));
        flightDto.setPassengersAmount(2);

        return flightDto;
    }

    //@FIXME refactor - copy paste to some common class
    private UserDto prepareUserDto() {
        UserDto userDto = new UserDto();
        userDto.setLogin("test");
        userDto.setPassword("testPW");
        return userDto;
    }


}
