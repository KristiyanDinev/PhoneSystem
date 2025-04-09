package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.dto.LoginUserDto;
import me.kristiyandinev.PhoneSystem.dto.RegisterUserDto;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;


@RestController
@EnableWebMvc
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Value("${templates.login}")
    private String loginTemplate;

    @Value("${templates.register}")
    private String registerTemplate;

    @Value("${templates.index}")
    private String indexTemplate;

    @Value("${page.max_page_size}")
    private int maxPageSize;

    @Value("${variables.list_of_phones}")
    private String list_of_phones;

    private String redirect = "redirect:";


    @GetMapping("/")
    public ModelAndView index(HttpSession session, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView();
        if (pageable.getPageSize() > maxPageSize) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        UserEntity userEntityFromSession = userService.getUserEntityFromSession(session);
        Optional<UserEntity> optionalUser = userService.findById(userEntityFromSession == null
                ? null : userEntityFromSession.id);

        if (optionalUser.isEmpty()) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        // user is logged in and wants to see his phones
        UserEntity userEntity = optionalUser.get();
        Page<PhoneEntity> phones = userService.findPhonesByUser(userEntity, pageable);

        modelAndView.addObject(list_of_phones, phones.getContent());
        modelAndView.addObject("user", userEntity);
        modelAndView.setViewName(indexTemplate);
        return modelAndView;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView login(HttpSession session, @ModelAttribute LoginUserDto loginUserDto) {
        Optional<UserEntity> optionalUser = userService.login(loginUserDto);

        ModelAndView modelAndView = new ModelAndView();
        if (optionalUser.isEmpty()) {
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
            return modelAndView;
        }

        UserEntity userEntity = optionalUser.get();

        try {
            userService.setUserEntityToSession(session, userEntity);
            modelAndView.setViewName(redirect+"/");

        } catch (Exception e) {
            modelAndView.setStatus(HttpStatus.UNAUTHORIZED);
            return modelAndView;
        }

        return modelAndView;
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView register(HttpSession session, @ModelAttribute RegisterUserDto registerUserDto) {
        Optional<UserEntity> optionalUser = userService.register(registerUserDto);
        ModelAndView modelAndView = new ModelAndView();
        if (optionalUser.isEmpty()) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        UserEntity userEntity = optionalUser.get();

        try {
            userService.setUserEntityToSession(session, userEntity);
            modelAndView.setViewName(redirect+"/");

        } catch (Exception e) {
            modelAndView.setViewName(redirect+loginTemplate);
        }

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView loginHTML() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(loginTemplate);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerHTML() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(registerTemplate);
        return modelAndView;
    }
}
