package tech.lideo.flywithus.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.lideo.flywithus.FlywithusApplication;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
@ContextConfiguration(classes = FlywithusApplication.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createReservation() throws Exception {

        mockMvc.perform(post("/api/reservation")
                .contentType(APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}