package me.kristiyandinev.PhoneSystem.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class UserRepo implements JpaRepository<UserEntity, Integer> {

    private EntityManager entityManager;

    private SimpleJpaRepository<UserEntity, Integer> simpleJpaRepository;

    @Autowired
    public UserRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        simpleJpaRepository = new SimpleJpaRepository<>(UserEntity.class, entityManager);
    }

    @Override
    public void flush() {
        simpleJpaRepository.flush();
    }

    @Override
    public <S extends UserEntity> S saveAndFlush(S entity) {
        return simpleJpaRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends UserEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return simpleJpaRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<UserEntity> entities) {
        simpleJpaRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ints) {
        simpleJpaRepository.deleteAllByIdInBatch(ints);
    }

    @Override
    public void deleteAllInBatch() {
        simpleJpaRepository.deleteAllInBatch();
    }

    @Deprecated
    public UserEntity getOne(Integer aInt) {
        return simpleJpaRepository.getOne(aInt);
    }

    @Override
    @Deprecated
    public UserEntity getById(Integer aInt) {
        return simpleJpaRepository.getById(aInt);
    }

    @Override
    public UserEntity getReferenceById(Integer aInt) {
        return simpleJpaRepository.getReferenceById(aInt);
    }

    @Override
    public <S extends UserEntity> Optional<S> findOne(Example<S> example) {
        return simpleJpaRepository.findOne(example);
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example) {
        return simpleJpaRepository.findAll(example);
    }

    @Override
    public <S extends UserEntity> List<S> findAll(Example<S> example, Sort sort) {
        return simpleJpaRepository.findAll(example, sort);
    }

    @Override
    public <S extends UserEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return simpleJpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends UserEntity> long count(Example<S> example) {
        return simpleJpaRepository.count(example);
    }

    @Override
    public <S extends UserEntity> boolean exists(Example<S> example) {
        return simpleJpaRepository.exists(example);
    }

    @Override
    public <S extends UserEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return simpleJpaRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends UserEntity> S save(S entity) {
        return simpleJpaRepository.save(entity);
    }

    @Override
    public <S extends UserEntity> List<S> saveAll(Iterable<S> entities) {
        return simpleJpaRepository.saveAll(entities);
    }

    @Override
    public Optional<UserEntity> findById(Integer aInt) {
        return simpleJpaRepository.findById(aInt);
    }

    @Override
    public boolean existsById(Integer aInt) {
        return simpleJpaRepository.existsById(aInt);
    }

    @Override
    public List<UserEntity> findAll() {
        return simpleJpaRepository.findAll();
    }

    @Override
    public List<UserEntity> findAllById(Iterable<Integer> ints) {
        return simpleJpaRepository.findAllById(ints);
    }

    @Override
    public long count() {
        return simpleJpaRepository.count();
    }

    @Override
    public void deleteById(Integer aInt) {
        simpleJpaRepository.deleteById(aInt);
    }

    @Override
    public void delete(UserEntity entity) {
        simpleJpaRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ints) {
        simpleJpaRepository.deleteAllById(ints);
    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {
        simpleJpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<UserEntity> criteriaQuery = criteriaBuilder.createCriteriaDelete(UserEntity.class);
        criteriaQuery.from(UserEntity.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }

    @Override
    public List<UserEntity> findAll(Sort sort) {
        return simpleJpaRepository.findAll(sort);
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return simpleJpaRepository.findAll(pageable);
    }

    public Optional<UserEntity> login(UserEntity userEntity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserEntity> criteriaQuery = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = criteriaQuery.from(UserEntity.class);

        criteriaQuery = criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("email"), userEntity.email),
                        criteriaBuilder.equal(root.get("password"), userEntity.password)
                )
        );

        List<UserEntity> userEntities = entityManager.createQuery(criteriaQuery).getResultList();
        return userEntities.isEmpty() ? Optional.empty() : Optional.of(userEntities.getFirst());
    }
}
