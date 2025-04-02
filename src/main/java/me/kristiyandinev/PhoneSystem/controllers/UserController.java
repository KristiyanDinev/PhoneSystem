package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
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


    @GetMapping("/")
    public ModelAndView index(HttpSession session, Pageable pageable) {
        //Pageable pageable = PageRequest.of(size, page, Sort.by(sort));

        ModelAndView modelAndView = new ModelAndView();
        if (pageable.getPageSize() >= maxPageSize) {
            modelAndView.setViewName(loginTemplate);
            return modelAndView;
        }

        Optional<User> optionalUser = userService.findById(
                                userService.getUserIdFromSession(session));

        if (optionalUser.isEmpty()) {
            modelAndView.setViewName(loginTemplate);
            return modelAndView;
        }

        // user is logged in and wants to see his phones
        User user = optionalUser.get();
        Page<Phone> phones = userService.findPhonesByUser(user, pageable);

        modelAndView.addObject(list_of_phones, phones.getContent());
        modelAndView.setViewName(indexTemplate);
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody User user) {
        Optional<User> optionalUser = userService.login(user);
        if (optionalUser.isEmpty()) {
            return loginTemplate;
        }

        User userDb = optionalUser.get();
        userService.setUserIdToSession(session, userDb.id);
        return "redirect:"+indexTemplate;
    }

    @PostMapping("/register")
    public String register(HttpSession session, @RequestBody User user) {
        Optional<User> optionalUser = userService.register(user);
        if (optionalUser.isEmpty()) {
            return loginTemplate;
        }

        User userDb = optionalUser.get();
        userService.setUserIdToSession(session, userDb.id);
        return "redirect:"+indexTemplate;
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
