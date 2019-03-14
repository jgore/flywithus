package tech.lideo.flywithus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.repository.FlightRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @Before
    public void setup() {

        FlightDto flightDto = new FlightDto();
        flightDto.setId(0L);
        when(flightRepository.getAll()).thenReturn(Collections.singletonList(flightDto));
        FlightDto dto = new FlightDto();
        dto.setId(0L);
        when(flightRepository.get(any(Long.class))).thenReturn(flightDto);
    }

    @Test
    public void create() {
        FlightDto flightDto = prepareFlight();
        flightService.create(flightDto);

        verify(flightRepository, times(1)).create(any(FlightDto.class));
    }

    @Test
    public void getAll() {
        flightService.getAll();
        verify(flightRepository, times(1)).getAll();
    }

    private FlightDto prepareFlight() {
        FlightDto flightDto = new FlightDto();
        flightDto.setArrivalCity("wroclaw");
        flightDto.setArrivalDate(LocalDate.now());
        flightDto.setDepartureCity("London");
        flightDto.setDepartureDate(LocalDate.now());

        flightDto.setPrice(new BigDecimal(15));

        return flightDto;
    }

    @Test
    public void get() {
        FlightDto flightDto = flightRepository.getAll().get(0);
        FlightDto flightById = flightRepository.get(flightDto.getId());

        assertNotNull(flightById);
    }
}
