package tech.lideo.flywithus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;

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
        BigDecimal price = priceService.calculateReservation(null, new FlightDto(), reservationDto);

        assertThat(price, equalTo(new BigDecimal(50).setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP)));

    }

    @Test
    public void calculateReservation__withFastBriefAndLoggedUser() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFastBriefing(true);
        BigDecimal price = priceService.calculateReservation(new UserDto(), new FlightDto(), reservationDto);


        assertThat(price, equalTo(new BigDecimal(47.50).setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_UP)));

    }
}