package me.kristiyandinev.PhoneSystem.services;

import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.dto.LoginUserDto;
import me.kristiyandinev.PhoneSystem.dto.RegisterUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService {
    UserEntity getUserEntityFromSession(HttpSession session);
    void setUserEntityToSession(HttpSession session, UserEntity userEntity) throws Exception;
    Optional<UserEntity> findById(Integer id);
    Page<PhoneEntity> findPhonesByUser(UserEntity userEntity, Pageable pageable);
    Optional<UserEntity> login(LoginUserDto userEntity);
    Optional<UserEntity> register(RegisterUserDto userEntity);
    String hashUserPassword(String password);
}
