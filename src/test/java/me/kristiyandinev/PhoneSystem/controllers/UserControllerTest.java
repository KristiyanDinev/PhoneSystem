package me.kristiyandinev.PhoneSystem.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
@Configuration
public class UserControllerTest {

    @Value("${test.url}")
    private String indexUrl;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private User user;

    public UserControllerTest() {
        mockMvc = standaloneSetup(new UserController()).build();
        objectMapper = new ObjectMapper();
        System.out.println("index: "+indexUrl);
        user = new User(null,
                "John",
                "s@example.com",
                "123", null);
    }


    @Test
    public void loads() throws Exception {
        mockMvc.perform(
                get(indexUrl)
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void register() throws Exception {
        mockMvc.perform(
                post(indexUrl+"register")
                .content(
                        objectMapper.writeValueAsString(
                                 user))
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(
                post(indexUrl+"login")
                        .content(
                                objectMapper.writeValueAsString(
                                        user))
        ).andExpect(
                status().isOk()
        );
    }
}
