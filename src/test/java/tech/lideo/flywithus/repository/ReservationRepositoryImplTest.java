package tech.lideo.flywithus.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.ReservationStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationRepositoryImplTest {

    public static final String TEST_EMAIL = "testEmail";

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
    public void getByEmail__shouldReturnsReservationdByEmail() {
        List<ReservationDto> byEmail = reservationRepository.getByEmail(TEST_EMAIL);
        assertThat(byEmail.size(), equalTo(1));

        assertEquals(byEmail.get(0).getUserEmail(), TEST_EMAIL);
    }

    @Test
    public void getAll__shouldReturnAllResevations() {

        List<ReservationDto> byEmail = reservationRepository.getAll();
        assertThat(byEmail.size(), equalTo(1));
    }


    @Test
    public void create() {
        reservationRepository.create(prepareReservation());

        assertThat(reservationRepository.getCount(), equalTo(2));
    }

    @Test
    public void get() {
        ReservationDto reservationDto = reservationRepository.getAll().get(0);
        assertNotNull(reservationDto);

        assertThat( reservationDto.getFlightId(), equalTo(1L));
        assertThat(reservationDto.getUserEmail(), equalTo(TEST_EMAIL));
    }


    @Test
    public void getBySecretCode() {
        ReservationDto reservationDto = reservationRepository.getAll().get(0);

        UUID reservationSecretCode = reservationDto.getReservationSecretCode();

        ReservationDto bySecretCode = reservationRepository.getBySecretCode(reservationSecretCode);

        assertEquals( reservationDto, bySecretCode);
    }

    @Test
    public void updateStatus() {
        ReservationDto reservationDto = reservationRepository.getAll().get(0);
        reservationDto.setStatus(ReservationStatus.CANCELLED);

        reservationRepository.updateStatus( reservationDto);

        ReservationDto reservationDto1 = reservationRepository.get(reservationDto.getId());

        assertEquals(reservationDto1.getStatus(), ReservationStatus.CANCELLED);
    }

    private ReservationDto prepareReservation() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFlightId(1L);
        reservationDto.setUserEmail(TEST_EMAIL);
        reservationDto.setPrice(new BigDecimal(9));
        return reservationDto;
    }
}