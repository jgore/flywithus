package tech.lideo.flywithus.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.ReservationDto;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.FlightRepository;
import tech.lideo.flywithus.repository.UserRepository;
import tech.lideo.flywithus.service.ReservationService;
import tech.lideo.flywithus.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = FlyWithUsApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private ReservationService reservationService;


    @Test
    public void saveUser() throws Exception {

        UserDto userdto = new UserDto();
        userdto.setEmail("1234g@gmail.com");
        userdto.setPassword("2");
        String jsonUser = FlyWithUsApplication.gson.toJson(userdto);

        mockMvc.perform(post("/api/user")
                .contentType(APPLICATION_JSON)
                .content(jsonUser))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(userService, times(1)).create(any(UserDto.class));
    }
}