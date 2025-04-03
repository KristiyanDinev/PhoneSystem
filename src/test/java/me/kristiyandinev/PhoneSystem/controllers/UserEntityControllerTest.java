package me.kristiyandinev.PhoneSystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.net.URL;
import java.net.http.HttpClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserEntityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String indexUrl;
    private ObjectMapper objectMapper;

    public UserEntityControllerTest() {
        objectMapper = new ObjectMapper();
        indexUrl = "http://127.0.0.1:8080/";
    }


    @Test
    public void indexGet() throws Exception {
        mockMvc.perform(
                get(indexUrl)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        );
    }

    @Test
    public void registerGet() throws Exception {
        mockMvc.perform(
                get(indexUrl+"register")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void userPost() throws Exception {
        LoginUserModel loginUserModel = new LoginUserModel("john@example.com", "123");
        RegisterUserModel registerUserModel = new RegisterUserModel(
                "John", "john@example.com", "123");
        
        mockMvc.perform(
                post(indexUrl+"register")
                        .content(objectMapper.writeValueAsString(registerUserModel))
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
        mockMvc.perform(
                post(indexUrl+"login")
                .content(objectMapper.writeValueAsString(loginUserModel))
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
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }
}
