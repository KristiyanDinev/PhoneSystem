package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;


@Controller
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
    public String index(Model model, Pageable pageable, HttpSession session) {
        if (pageable.getPageSize() >= maxPageSize) {
            return indexTemplate;
        }

        Optional<User> optionalUser = userService.findById(
                                userService.getUserIdFromSession(session));

        if (optionalUser.isEmpty()) {
            return loginTemplate;
        }

        // user is logged in and wants to see his phones
        User user = optionalUser.get();
        Page<Phone> phones = userService.findPhonesByUser(user, pageable);
        model.addAttribute(list_of_phones, phones.getContent());
        return indexTemplate;
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
    public String loginHTML() {
        return loginTemplate;
    }

    @GetMapping("/register")
    public String registerHTML() {
        return registerTemplate;
    }
}
