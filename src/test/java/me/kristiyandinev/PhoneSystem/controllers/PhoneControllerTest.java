package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.Cookie;
import me.kristiyandinev.PhoneSystem.ISetup;
import me.kristiyandinev.PhoneSystem.database.repositories.IPhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.spring_session.ISpringSessionAttributesRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.spring_session.ISpringSessionRepository;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneControllerTest implements ISetup {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUserRepository userRepository;

    private String phone;

    public PhoneControllerTest() {
        phone = "+3598781231";
    }


    @Test
    @DisplayName("PhoneController with MockMVC get requests and cookies.")
    public void phoneGet() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/phone")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        ).andExpect(
                redirectedUrl("login")
        ).andReturn();

        Cookie sessionCookie = mvcResult.getResponse().getCookie("SESSION");

        RegisterUserDto registerUserDto = new RegisterUserDto("John",
                "john2@example.com", "123");

        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserDto.name)
                                .param("email", registerUserDto.email)
                                .param("password", registerUserDto.password)
                                .cookie(sessionCookie)
                                .accept(MediaType.ALL)
                                )

                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));

        mockMvc.perform(
                get("/phone")
                .accept(MediaType.ALL)
                .cookie(sessionCookie)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }


    @Test
    @DisplayName("PhoneController with MockMVC post requests.")
    public void phonePost() throws Exception {
        RegisterUserDto registerUserDto = new RegisterUserDto("John",
                "john3@example.com", "123");

        MvcResult result = mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserDto.name)
                                .param("email", registerUserDto.email)
                                .param("password", registerUserDto.password))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound())
                .andReturn();

        Cookie cookie = result.getResponse().getCookie("SESSION");

        mockMvc.perform(
                post("/phone")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .cookie(cookie)
                .param("phone", phone)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        ).andExpect(
                redirectedUrl("/")
        );

        mockMvc.perform(
                post("/delphone")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .cookie(cookie)
                .param("phone", phone)
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        ).andExpect(
                redirectedUrl("/")
        );
    }


    @Autowired
    private ISpringSessionAttributesRepository springSessionAttributesRepository;

    @Autowired
    private ISpringSessionRepository springSessionRepository;

    @Autowired
    private IPhoneRepository phoneRepository;


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
