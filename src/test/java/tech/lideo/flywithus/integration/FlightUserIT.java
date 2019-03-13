package tech.lideo.flywithus.integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightUserIT {

    private final String TEST_LOGIN = "testLogin";
    private final String TEST_PASSWORD = "testPassword";

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testManyFlights()
    {
        for( int i=0;i<100;i++)
        {
            flightRepository.create(prepareFlight());
        }

        for( int i=0;i<100;i++)
        {
            userRepository.create(prepareUserDto());
        }

        flightRepository.getCount();
    }

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

    private UserDto prepareUserDto()
    {
        UserDto userDto = new UserDto();
        userDto.setLogin(TEST_LOGIN+Math.random());
        userDto.setPassword(TEST_PASSWORD);
        return userDto;
    }
}