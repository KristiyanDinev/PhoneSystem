package me.kristiyandinev.PhoneSystem.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@Transactional
public class UserRepo implements JpaRepository<User, Integer> {

    private EntityManager entityManager;

    private SimpleJpaRepository<User, Integer> simpleJpaRepository;

    @Autowired
    public UserRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        simpleJpaRepository = new SimpleJpaRepository<>(User.class, entityManager);
    }

    @Override
    public void flush() {
        simpleJpaRepository.flush();
    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return simpleJpaRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return simpleJpaRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {
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
    public User getOne(Integer aInt) {
        return simpleJpaRepository.getOne(aInt);
    }

    @Override
    @Deprecated
    public User getById(Integer aInt) {
        return simpleJpaRepository.getById(aInt);
    }

    @Override
    public User getReferenceById(Integer aInt) {
        return simpleJpaRepository.getReferenceById(aInt);
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return simpleJpaRepository.findOne(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return simpleJpaRepository.findAll(example);
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return simpleJpaRepository.findAll(example, sort);
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return simpleJpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return simpleJpaRepository.count(example);
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return simpleJpaRepository.exists(example);
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return simpleJpaRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends User> S save(S entity) {
        return simpleJpaRepository.save(entity);
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return simpleJpaRepository.saveAll(entities);
    }

    @Override
    public Optional<User> findById(Integer aInt) {
        return simpleJpaRepository.findById(aInt);
    }

    @Override
    public boolean existsById(Integer aInt) {
        return simpleJpaRepository.existsById(aInt);
    }

    @Override
    public List<User> findAll() {
        return simpleJpaRepository.findAll();
    }

    @Override
    public List<User> findAllById(Iterable<Integer> ints) {
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
    public void delete(User entity) {
        simpleJpaRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> ints) {
        simpleJpaRepository.deleteAllById(ints);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {
        simpleJpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<User> criteriaQuery = criteriaBuilder.createCriteriaDelete(User.class);
        criteriaQuery.from(User.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }

    @Override
    public List<User> findAll(Sort sort) {
        return simpleJpaRepository.findAll(sort);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return simpleJpaRepository.findAll(pageable);
    }
}
