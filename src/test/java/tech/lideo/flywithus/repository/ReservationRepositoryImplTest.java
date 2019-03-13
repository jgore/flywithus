package tech.lideo.flywithus.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.ReservationDto;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryImplTest {

    public static final String TEST_LOGIN = "testLogin";

    @Autowired
    private ReservationRepository reservationRepository;

    @Before
    public void setup() {
        reservationRepository.create(prepareReservation());
    }

    @After
    public void tearDown() {
        reservationRepository.deleteAll();
    }

    @Test
    public void getByLogin() {
        List<ReservationDto> byLogin = reservationRepository.getByLogin(TEST_LOGIN);
        assertThat(byLogin.size(), equalTo(1));
    }

    @Test
    public void create() {
        reservationRepository.create(prepareReservation());

        assertThat(reservationRepository.getCount(), equalTo(2));
    }

    private ReservationDto prepareReservation() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFlightId(1L);
        reservationDto.setUserLogin(TEST_LOGIN);
        reservationDto.setPrice(new BigDecimal(9));
        return reservationDto;
    }

}