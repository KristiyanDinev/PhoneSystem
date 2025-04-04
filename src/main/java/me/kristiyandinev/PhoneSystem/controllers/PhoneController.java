package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.database.repositories.PhoneRepository;
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
    private PhoneRepository phoneRepository;

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
        // add phone
        Integer id = userService.getUserIdFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (id == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        modelAndView.setViewName(addPhoneTemplate);
        return modelAndView;
    }

    @PostMapping(value = "/phone", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView registerPhone(HttpSession session, @RequestParam("phone") String phone) {
        Integer id = userService.getUserIdFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (id == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.id = id;
        phoneRepository.save(new PhoneEntity(phone, userEntity, null));

        modelAndView.setViewName(redirect+indexTemplate);
        return modelAndView;
    }

    @PostMapping(value = "/delphone", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView deletePhone(HttpSession session, @RequestParam("phone") String phone) {
        Integer id = userService.getUserIdFromSession(session);

        ModelAndView modelAndView = new ModelAndView();
        if (id == null) {
            modelAndView.setViewName(redirect+loginTemplate);
            return modelAndView;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.id = id;
        phoneRepository.delete(new PhoneEntity(phone, userEntity, null));

        modelAndView.setViewName(redirect+indexTemplate);
        return modelAndView;
    }
}
