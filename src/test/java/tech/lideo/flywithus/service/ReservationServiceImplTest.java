package tech.lideo.flywithus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.ReservationRepository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserService userService;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    @Before
    public void setup() {
        when(userService.getByEmail(any())).thenReturn(new UserDto());
        when(flightRepository.get(any())).thenReturn( new FlightDto());
    }

    @Test
    public void create__shouldFillDefaultFields() {
        ReservationDto reservationDto = reservationServiceImpl.create(new ReservationDto() );

        assertNotNull( reservationDto.getReservationSecretCode() );
        assertNotNull( reservationDto.getCreated() );

        verify(reservationRepository, times(1)).create(any(ReservationDto.class));
    }

    @Test
    public void getByEmail() {
        reservationServiceImpl.getByEmail("testEmail");
        verify(reservationRepository, times(1)).getByEmail(any());
    }
}