package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.Cookie;
import me.kristiyandinev.PhoneSystem.ISetup;
import me.kristiyandinev.PhoneSystem.database.repositories.IPhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.spring_session.ISpringSessionAttributesRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.spring_session.ISpringSessionRepository;
import me.kristiyandinev.PhoneSystem.dto.LoginUserDto;
import me.kristiyandinev.PhoneSystem.dto.RegisterUserDto;
import me.kristiyandinev.PhoneSystem.database.repositories.IUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest implements ISetup {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("UserController with MockMVC get requests.")
    public void userGet() throws Exception {
        mockMvc.perform(
                get("/")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        ).andExpect(
                redirectedUrl("login")
        );

        mockMvc.perform(
                get("/login")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );

        mockMvc.perform(
                get("/register")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }


    @Test
    @DisplayName("UserController with MockMVC post requests and cookies.")
    public void userPost() throws Exception {
        LoginUserDto loginUserDto = new LoginUserDto("john1@example.com", "123");

        LoginUserDto invalidLoginUserDto = new LoginUserDto("1", "1");

        RegisterUserDto registerUserDto = new RegisterUserDto("John",
                "john1@example.com", "123");

        MvcResult result = mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserDto.name)
                                .param("email", registerUserDto.email)
                                .param("password", registerUserDto.password))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andReturn();

        Cookie cookie = result.getResponse().getCookie("SESSION");

        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .cookie(cookie)
                                .param("email", loginUserDto.email)
                                .param("password", loginUserDto.password)
                )
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(
                        get("/")
                                .cookie(cookie)
                )
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk());

        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .cookie(cookie)
                                .param("email", invalidLoginUserDto.email)
                                .param("password", invalidLoginUserDto.password)
                )
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isUnauthorized());
    }





    @Autowired
    private ISpringSessionAttributesRepository springSessionAttributesRepository;

    @Autowired
    private ISpringSessionRepository springSessionRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private IUserRepository userRepository;


    @Override
    public void setUp() {}

    @Override
    @AfterEach
    public void tearDown() {
        phoneRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        springSessionAttributesRepository.deleteAllInBatch();
        springSessionRepository.deleteAllInBatch();
    }
}
