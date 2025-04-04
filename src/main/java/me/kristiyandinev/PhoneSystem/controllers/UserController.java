package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
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
        //Pageable pageable = PageRequest.of(size, page, Sort.by(sort));

        ModelAndView modelAndView = new ModelAndView();
        if (pageable.getPageSize() > maxPageSize) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        Optional<UserEntity> optionalUser = userService.findById(
                                userService.getUserIdFromSession(session));

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
    public ModelAndView login(HttpSession session, @ModelAttribute LoginUserModel loginUserModel) {
        Optional<UserEntity> optionalUser = userService.login(loginUserModel);
        ModelAndView modelAndView = new ModelAndView();
        if (optionalUser.isEmpty()) {
            modelAndView.setViewName(loginTemplate);
            modelAndView.setStatus(HttpStatusCode.valueOf(401));
            return modelAndView;
        }

        UserEntity userEntityDb = optionalUser.get();
        userService.setUserIdToSession(session, userEntityDb.id);

        modelAndView.setViewName(redirect+"/");
        return modelAndView;
    }

    @PostMapping(value = "/register",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView register(HttpSession session, @ModelAttribute RegisterUserModel registerUserModel) {
        Optional<UserEntity> optionalUser = userService.register(registerUserModel);
        ModelAndView modelAndView = new ModelAndView();
        if (optionalUser.isEmpty()) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        UserEntity userEntityDb = optionalUser.get();
        userService.setUserIdToSession(session, userEntityDb.id);

        modelAndView.setViewName(redirect+"/");
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
