package tech.lideo.flywithus.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import tech.lideo.flywithus.controller.dto.FlightDto;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.ReservationStatus;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ReservationServiceImplTest {

    private UUID TEST_UUID = UUID.randomUUID();

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

    private String autoCancelDays = "2";
    private String cancelDays = "5";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(userService.getByEmail(any())).thenReturn(new UserDto());
        FlightDto flightDto = new FlightDto();
        flightDto.setDepartureDate( LocalDate.now());
        when(flightRepository.get(any())).thenReturn(flightDto);
        when(reservationRepository.getBySecretCode(any(UUID.class))).thenReturn(new ReservationDto());
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setCreated( LocalDate.now().minusMonths(1));
        reservationDto.setStatus(ReservationStatus.CREATED);

        when( reservationRepository.getAll()).thenReturn(Collections.singletonList(reservationDto));
        when( reservationRepository.getByStatus(ReservationStatus.CREATED)).thenReturn(Collections.singletonList(reservationDto));
        ReflectionTestUtils.setField(reservationServiceImpl, "autoCancelDays", autoCancelDays);
        ReflectionTestUtils.setField(reservationServiceImpl, "cancelDays", cancelDays);
    }


    @Test
    public void create__shouldFillDefaultFields() {
        reservationServiceImpl.create(new ReservationDto());


        verify(reservationRepository, times(1)).create(any(ReservationDto.class));
    }

    @Test
    public void getByEmail() {
        reservationServiceImpl.getByEmail("testEmail");
        verify(reservationRepository, times(1)).getByEmail(any());
    }

    @Test
    public void cancel() {
        FlightDto longDepartureFlight = new FlightDto();
        longDepartureFlight.setDepartureDate(LocalDate.now().minusDays(100));
        when( flightRepository.get(any()) ).thenReturn(longDepartureFlight);

        reservationServiceImpl.cancel(TEST_UUID);

        verify(reservationRepository, times(1)).updateStatus(any(ReservationDto.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cancel__reservationWithEarlyDepartureShouldThrowEx() {
        reservationServiceImpl.cancel(UUID.randomUUID());


        verify(reservationRepository, times(1)).updateStatus(any(ReservationDto.class));
    }


    @Test
    public void autoCancelExpiredReservations() {
        reservationServiceImpl.autoCancelExpiredReservations();

        verify(reservationRepository, times(1)).getByStatus(ReservationStatus.CREATED);
        verify(reservationRepository, times(1)).updateStatus( any(ReservationDto.class));

    }
}