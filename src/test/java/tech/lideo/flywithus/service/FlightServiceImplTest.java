package tech.lideo.flywithus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.repository.FlightRepository;

import java.math.BigDecimal;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Test
    public void create() {
        FlightDto flightDto = prepareFlight();
        flightService.create(flightDto);

        verify(flightRepository, times(1)).create(any(FlightDto.class));
    }

    @Test
    public void get() {
        flightService.get(0L);
        verify(flightRepository, times(1)).get(any(Long.class));
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
}
