package me.kristiyandinev.PhoneSystem.services;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Integer getUserIdFromSession(HttpSession session);
    void setUserIdToSession(HttpSession session, int id);
    Optional<User> findById(Integer id);
    Page<Phone> findPhonesByUser(User user, Pageable pageable);
    Optional<User> login(User user);
    Optional<User> register(User user);
}
