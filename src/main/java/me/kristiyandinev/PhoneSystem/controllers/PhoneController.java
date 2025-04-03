package me.kristiyandinev.PhoneSystem.controllers;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.repos.PhoneRepo;
import me.kristiyandinev.PhoneSystem.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class PhoneController {

    @Autowired
    private PhoneRepo phoneRepo;

    @Autowired
    private UserServiceImpl userService;

    @Value("${templates.addphone}")
    private String addPhoneTemplate;

    @Value("${templates.login}")
    private String loginTemplate;

    @Value("${templates.index}")
    private String indexTemplate;


    @GetMapping("/phone")
    public String phoneHTML() {
        return addPhoneTemplate;
    }

    @PostMapping("/phone")
    public String registerPhone(HttpSession session, @RequestBody String phone) {
        Integer id = userService.getUserIdFromSession(session);
        if (id == null) {
            return loginTemplate;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.id = id;
        phoneRepo.save(new PhoneEntity(phone, userEntity, null));
        return indexTemplate;
    }

}
