package me.kristiyandinev.PhoneSystem.controllers;

import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import me.kristiyandinev.PhoneSystem.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private String phone;

    public PhoneControllerTest() {
        phone = "+3598781231";
    }


    //@Test
    public void phoneGet() throws Exception {
        mockMvc.perform(
                get("/phone")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        );

        RegisterUserModel registerUserModel = new RegisterUserModel("John",
                "john@example.com", "123");

        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserModel.name)
                                .param("email", registerUserModel.email)
                                .param("password", registerUserModel.password)
                                )

                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound());

        mockMvc.perform(
                get("/phone")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );

        userRepository.deleteAll();
    }


    //@Test
    public void phonePost() throws Exception {
        RegisterUserModel registerUserModel = new RegisterUserModel("John",
                "john@example.com", "123");

        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserModel.name)
                                .param("email", registerUserModel.email)
                                .param("password", registerUserModel.password))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound());


        mockMvc.perform(
                post("/phone")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("phone", phone)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        );

        mockMvc.perform(
                post("/delphone")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("phone", phone)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        );

        userRepository.deleteAll();
    }
}
