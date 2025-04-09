package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.services.impl.PhoneServiceImpl;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class PhoneController {

    @Autowired
    private PhoneServiceImpl phoneService;

    @Autowired
    private UserServiceImpl userService;

    @Value("${templates.addphone}")
    private String addPhoneTemplate;

    @Value("${templates.login}")
    private String loginTemplate;

    @Value("${templates.index}")
    private String indexTemplate;

    private String redirect = "redirect:";


    @GetMapping("/phone")
    public ModelAndView phoneHTML(HttpSession session) {
        UserEntity userEntity = userService.getUserEntityFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (userEntity == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        modelAndView.setViewName(addPhoneTemplate);
        return modelAndView;
    }

    @PostMapping(value = "/phone", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerPhone(HttpSession session, @RequestParam("phone") String phone) {
        UserEntity userEntity = userService.getUserEntityFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (userEntity == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        phoneService.addPhone(phone, userEntity);

        modelAndView.setViewName(redirect+"/");
        return modelAndView;
    }

    @PostMapping(value = "/delphone", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView deletePhone(HttpSession session, @RequestParam("phone") String phone) {
        UserEntity userEntity = userService.getUserEntityFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (userEntity == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        phoneService.removePhone(phone, userEntity);

        modelAndView.setViewName(redirect+"/");
        return modelAndView;
    }
}
