package me.kristiyandinev.PhoneSystem.services.impl;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpSession;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import me.kristiyandinev.PhoneSystem.database.repositories.PhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.UserRepository;
import me.kristiyandinev.PhoneSystem.services.IUserService;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EncryptionUtil encryptionUtil;

    private String session_user_id = "user_id";


    private Optional<UserEntity> hashUserPassword(UserEntity userEntity) {
        try {
            userEntity.password = encryptionUtil.hash256String(userEntity.password);
            return Optional.of(userEntity);

        } catch (Exception a) {
            return Optional.empty();
        }
    }

    @Override
    @Nullable
    public Integer getUserIdFromSession(HttpSession session) {
        try {
            return Integer.parseInt(String.valueOf(session.getAttribute(session_user_id)));
        } catch (Exception a) {
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
        return userRepository.findById(id);
    }

    @Override
    public Page<PhoneEntity> findPhonesByUser(UserEntity userEntity, Pageable pageable) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.userEntity = userEntity;
        return phoneRepository.findAll(Example.of(phoneEntity), pageable);
    }


    @Override
    public Optional<UserEntity> login(LoginUserModel loginUserModel) {
        Optional<UserEntity> optionalUserEntity = hashUserPassword(new UserEntity(loginUserModel));
        if (optionalUserEntity.isEmpty()) {
            return optionalUserEntity;
        }

        return loginQuery(optionalUserEntity.get());
    }

    @Override
    public Optional<UserEntity> register(RegisterUserModel registerUserModel) {
        return hashUserPassword(
                new UserEntity(registerUserModel))
                .map(value -> userRepository.save(value));
    }




    private Optional<UserEntity> loginQuery(UserEntity userEntity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery = criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("email"), userEntity.email),
                        criteriaBuilder.equal(root.get("password"), userEntity.password)
                )
        );

        List<UserEntity> userEntities = entityManager
                .createQuery(criteriaQuery).getResultList();
        return userEntities.isEmpty() ? Optional.empty() : Optional.of(userEntities.getFirst());
    }
}
