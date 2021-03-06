package tech.lideo.flywithus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.service.price.PriceServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PriceServiceImplTest {

    @Autowired
    private PriceServiceImpl priceService;

    @Value("${flywithus.currency}")
    private String currencyString;

    private Currency currency;

    @Before
    public void setup() {
        currency = Currency.getInstance(currencyString);
    }

    @Test
    public void calculateReservation__withFastBrief() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFastBriefing(true);
        reservationDto.setPassengersAmount(2);
        FlightDto flightDto = new FlightDto();
        flightDto.setPrice(new BigDecimal(50));
        BigDecimal price = priceService.calculateReservation(null, flightDto, reservationDto);

        assertThat(price, equalTo(new BigDecimal(200).setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP)));

    }

    @Test
    public void calculateReservation__withFastBriefAndLoggedUser() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFastBriefing(true);
        reservationDto.setPassengersAmount(1);
        FlightDto flightDto = new FlightDto();
        flightDto.setPrice(new BigDecimal(50));
        BigDecimal price = priceService.calculateReservation(new UserDto(), flightDto, reservationDto);


        assertThat(price, equalTo(new BigDecimal(95.00).setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP)));

    }
}