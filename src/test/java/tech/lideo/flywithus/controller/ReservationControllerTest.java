package tech.lideo.flywithus.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.UserRepository;
import tech.lideo.flywithus.service.ReservationService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@ContextConfiguration(classes = FlyWithUsApplication.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ReservationService reservationService;

    @Test
    public void createReservation() throws Exception {

        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setFlightId(1L);
        reservationDto.setUserEmail("2");
        reservationDto.setPassengersAmount(1);
        String jsonFlight = FlyWithUsApplication.gson.toJson(reservationDto);


        mockMvc.perform(post("/api/reservation")
                .contentType(APPLICATION_JSON)
                .content(jsonFlight))
                .andDo(print())
                .andExpect(status().isOk());

    }
}