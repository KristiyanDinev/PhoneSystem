package me.kristiyandinev.PhoneSystem.services.impl;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import me.kristiyandinev.PhoneSystem.repos.PhoneRepo;
import me.kristiyandinev.PhoneSystem.repos.UserRepo;
import me.kristiyandinev.PhoneSystem.services.IUserService;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PhoneRepo phoneRepo;

    @Autowired
    private EncryptionUtil encryptionUtil;

    private String session_user_id = "user_id";


    private Optional<User> encryptUserPassword(User user) {
        try {
            user.password = encryptionUtil.encrypt(user.password);
            return Optional.of(user);

        } catch (Exception _) {
            return Optional.empty();
        }
    }

    @Override
    @Nullable
    public Integer getUserIdFromSession(HttpSession session) {
        try {
            return Integer.parseInt(String.valueOf(session.getAttribute(session_user_id)));
        } catch (Exception _) {
            return null;
        }
    }

    @Override
    public void setUserIdToSession(HttpSession session, int id) {
        session.setAttribute(session_user_id, id);
    }


    @Override
    public Optional<User> findById(@Nullable Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepo.findById(id);
    }

    @Override
    public Page<Phone> findPhonesByUser(User user, Pageable pageable) {
        Phone phone = new Phone();
        phone.user = user;
        return phoneRepo.findAll(Example.of(phone), pageable);
    }

    @Override
    public Optional<User> login(User user) {
        Optional<User> optionalUser = encryptUserPassword(user);
        if (optionalUser.isEmpty()) {
            return optionalUser;
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("email", exact())
                .withMatcher("password", exact());

        return userRepo.findOne(Example.of(optionalUser.get(), matcher));
    }

    @Override
    public Optional<User> register(User user) {
        return encryptUserPassword(user).map(value -> userRepo.save(value));
    }
}
