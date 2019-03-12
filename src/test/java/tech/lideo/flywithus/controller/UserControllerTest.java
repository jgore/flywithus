package tech.lideo.flywithus.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tech.lideo.flywithus.FlywithusApplication;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = FlywithusApplication.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProduct() throws Exception {
        mockMvc.perform(get("/api/user/1")
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void saveProduct() throws Exception {
        mockMvc.perform(post("/api/user")
                .contentType(APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}