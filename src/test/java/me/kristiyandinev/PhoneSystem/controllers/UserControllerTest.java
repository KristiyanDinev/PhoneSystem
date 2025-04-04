package me.kristiyandinev.PhoneSystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import me.kristiyandinev.PhoneSystem.database.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    public UserControllerTest() {
        super();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void indexGet() throws Exception {
        mockMvc.perform(
                get("/")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isFound()
        );
    }

    @Test
    public void registerGet() throws Exception {
        mockMvc.perform(
                get("/register")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void loginGet() throws Exception {
        mockMvc.perform(
                get("/login")
        ).andDo(
                MockMvcResultHandlers.log()
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void userPost() throws Exception {
        LoginUserModel loginUserModel = new LoginUserModel("john@example.com", "123");

        LoginUserModel invalidLoginUserModel = new LoginUserModel("1", "1");
        invalidLoginUserModel.setEmail("1");
        invalidLoginUserModel.setPassword("1");

        RegisterUserModel registerUserModel = new RegisterUserModel("John",
                "john@example.com", "123");


        /*
        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registerUserModel)))
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isFound());

                // for forms
                @PostMapping(value = "/login", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(HttpSession session, @ModelAttribute LoginUserModel loginUserModel) {}

    // for json requests
    @PostMapping("/login")
    public ModelAndView login(HttpSession session, @RequestBody LoginUserModel loginUserModel) {}


                */

        mockMvc.perform(
                        post("/register")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("name", registerUserModel.name)
                                .param("email", registerUserModel.email)
                                .param("password", registerUserModel.password))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("email", loginUserModel.email)
                                .param("password", loginUserModel.password)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isFound());

        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                                .param("email", invalidLoginUserModel.email)
                                .param("password", invalidLoginUserModel.password)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized());

        userRepository.deleteAll();
    }
}
