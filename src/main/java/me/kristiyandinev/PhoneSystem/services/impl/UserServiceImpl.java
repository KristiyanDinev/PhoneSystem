package me.kristiyandinev.PhoneSystem.services.impl;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
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

import java.util.ArrayList;
import java.util.List;
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


    private Optional<UserEntity> hashUserPassword(UserEntity userEntity) {
        try {
            userEntity.password = encryptionUtil.hash256String(userEntity.password);
            return Optional.of(userEntity);

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
    public Optional<UserEntity> findById(@Nullable Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return userRepo.findById(id);
    }

    @Override
    public Page<PhoneEntity> findPhonesByUser(UserEntity userEntity, Pageable pageable) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.userEntity = userEntity;
        return phoneRepo.findAll(Example.of(phoneEntity), pageable);
    }

    @Override
    public Optional<UserEntity> login(LoginUserModel userEntity) {
        Optional<UserEntity> optionalUser = hashUserPassword(new UserEntity(userEntity));
        if (optionalUser.isEmpty()) {
            return optionalUser;
        }
        return userRepo.login(optionalUser.get());
    }

    @Override
    public Optional<UserEntity> register(RegisterUserModel registerUserModel) {
        return hashUserPassword(
                new UserEntity(registerUserModel))
                .map(value -> userRepo.save(value));
    }
}
