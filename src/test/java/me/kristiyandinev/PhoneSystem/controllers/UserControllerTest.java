package me.kristiyandinev.PhoneSystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String indexUrl;
    private ObjectMapper objectMapper;
    private User user;

    public UserControllerTest() {
        objectMapper = new ObjectMapper();
        indexUrl = "http://127.0.0.1:8080/";
        user = new User(null,
                "John",
                "s@example.com",
                "123", null);
    }


    @Test
    public void indexGet() throws Exception {
        mockMvc.perform(
                get(indexUrl)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void registerPost() throws Exception {
        mockMvc.perform(
                post(indexUrl+"register")
                .content(
                        objectMapper.writeValueAsString(
                                 user))
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void registerGet() throws Exception {
        mockMvc.perform(
                get(indexUrl+"register")
                        .content(
                                objectMapper.writeValueAsString(
                                        user))
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void loginPost() throws Exception {
        mockMvc.perform(
                post(indexUrl+"login")
                        .content(
                                objectMapper.writeValueAsString(
                                        user))
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void loginGet() throws Exception {
        mockMvc.perform(
                get(indexUrl+"login")
                        .content(
                                objectMapper.writeValueAsString(
                                        user))
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }
}
