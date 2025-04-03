package me.kristiyandinev.PhoneSystem.services;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    Integer getUserIdFromSession(HttpSession session);
    void setUserIdToSession(HttpSession session, int id);
    Optional<UserEntity> findById(Integer id);
    Page<PhoneEntity> findPhonesByUser(UserEntity userEntity, Pageable pageable);
    Optional<UserEntity> login(LoginUserModel userEntity);
    Optional<UserEntity> register(RegisterUserModel userEntity);
}
