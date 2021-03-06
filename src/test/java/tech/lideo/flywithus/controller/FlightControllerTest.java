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
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.UserRepository;
import tech.lideo.flywithus.service.FlightService;
import tech.lideo.flywithus.service.ReservationService;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
@ContextConfiguration(classes = FlyWithUsApplication.class)
public class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ReservationService reservationService;



    @Test
    public void getFlights() throws Exception {
        mockMvc.perform(get("/api/flight")
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}