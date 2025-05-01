package me.kristiyandinev.PhoneSystem.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.dto.LoginUserDto;
import me.kristiyandinev.PhoneSystem.dto.RegisterUserDto;
import me.kristiyandinev.PhoneSystem.database.repositories.IPhoneRepository;
import me.kristiyandinev.PhoneSystem.database.repositories.IUserRepository;
import me.kristiyandinev.PhoneSystem.services.IUserService;
import me.kristiyandinev.PhoneSystem.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPhoneRepository phoneRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EncryptionUtil encryptionUtil;

    private ObjectMapper objectMapper;

    private String session_user_dao = "user_dao";

    public UserServiceImpl() {
        objectMapper = new ObjectMapper().findAndRegisterModules();
    }


    @Nullable
    @Override
    public String hashUserPassword(String password) {
        try {
            return encryptionUtil.hash256String(password);

        } catch (Exception a) {
            return null;
        }
    }

    @Override
    @Nullable
    public UserEntity getUserEntityFromSession(HttpSession session) {
        try {
            return objectMapper.readValue(String.valueOf(session.getAttribute(session_user_dao)),
                     UserEntity.class);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setUserEntityToSession(HttpSession session, UserEntity userEntity) throws Exception {
        session.setAttribute(session_user_dao, objectMapper.writeValueAsString(userEntity));
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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Create query for selecting entities
        CriteriaQuery<PhoneEntity> criteriaQuery = criteriaBuilder.createQuery(PhoneEntity.class);
        Root<PhoneEntity> root = criteriaQuery.from(PhoneEntity.class);

        // Add where clause - use the field name 'userEntity', not the column name 'user_id'
        criteriaQuery = criteriaQuery.where(
                criteriaBuilder.equal(root.get("userEntity"), userEntity)
        );

        // Add sorting
        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                if (sortOrder.isAscending()) {
                    orders.add(criteriaBuilder.asc(root.get(sortOrder.getProperty())));
                } else {
                    orders.add(criteriaBuilder.desc(root.get(sortOrder.getProperty())));
                }
            }
            criteriaQuery = criteriaQuery.orderBy(orders);
        }

        // Execute the query with pagination
        TypedQuery<PhoneEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<PhoneEntity> phoneEntities = typedQuery.getResultList();

        // If we know the total is small or this is an internal API, we could use phoneEntities.size()
        // But this won't represent the true total across all pages
        return new PageImpl<>(phoneEntities, pageable, phoneEntities.size());
    }


    @Override
    public Optional<UserEntity> login(LoginUserDto loginUserDto) {
        loginUserDto.password = hashUserPassword(loginUserDto.password);
        if (loginUserDto.password == null) {
            return Optional.empty();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery = criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("email"), loginUserDto.email),
                        criteriaBuilder.equal(root.get("password"), loginUserDto.password)
                )
        );

        List<UserEntity> userEntities = entityManager
                .createQuery(criteriaQuery).getResultList();
        return userEntities.isEmpty() ? Optional.empty() : Optional.of(userEntities.getFirst());
    }

    @Override
    public Optional<UserEntity> register(RegisterUserDto registerUserDto) {
        registerUserDto.password = hashUserPassword(registerUserDto.password);
        if (registerUserDto.password == null) {
            return Optional.empty();
        }

        return Optional.of(userRepository.save(new UserEntity(registerUserDto)));
    }
}
