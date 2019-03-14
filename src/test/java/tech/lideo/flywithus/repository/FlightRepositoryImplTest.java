package tech.lideo.flywithus.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;

import java.math.BigDecimal;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightRepositoryImplTest {

    @Autowired
    private FlightRepository flightRepository;

    @Before
    public void setup() {
        FlightDto flightDto = prepareFlight();
        flightRepository.create(flightDto);
    }

    @After
    public void tearDown() {
        flightRepository.deleteAll();
    }

    @Test
    public void getAll() {
        assertThat(flightRepository.getAll().size(), equalTo(1));
    }

    @Test
    public void get() {
        FlightDto flightDto = flightRepository.getAll().get(0);
        assertNotNull(flightRepository.get(flightDto.getId()));
    }

    @Test
    public void create() {
        FlightDto flightDto = prepareFlight();
        flightRepository.create(flightDto);
        assertThat(flightRepository.getCount(), equalTo(2));
    }

    private FlightDto prepareFlight() {
        FlightDto flightDto = new FlightDto();
        flightDto.setArrivalCity("wroclaw");
        flightDto.setArrivalDate(new Date());
        flightDto.setDepartureCity("London");
        flightDto.setDepartureDate(new Date());

        flightDto.setPrice(new BigDecimal(15));

        return flightDto;
    }
}