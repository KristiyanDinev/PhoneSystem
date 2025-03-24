package me.kristiyandinev.PhoneSystem.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.Phone;
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
public class PhoneRepo implements JpaRepository<Phone, Long> {

    private EntityManager entityManager;

    private SimpleJpaRepository<Phone, Long> simpleJpaRepository;

    @Autowired
    public PhoneRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        simpleJpaRepository = new SimpleJpaRepository<>(Phone.class, entityManager);
    }

    @Override
    public void flush() {
        simpleJpaRepository.flush();
    }

    @Override
    public <S extends Phone> S saveAndFlush(S entity) {
        return simpleJpaRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Phone> List<S> saveAllAndFlush(Iterable<S> entities) {
        return simpleJpaRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Phone> entities) {
        simpleJpaRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        simpleJpaRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        simpleJpaRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Phone getOne(Long aLong) {
        return simpleJpaRepository.getOne(aLong);
    }

    @Override
    @Deprecated
    public Phone getById(Long aLong) {
        return simpleJpaRepository.getById(aLong);
    }

    @Override
    public Phone getReferenceById(Long aLong) {
        return simpleJpaRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Phone> Optional<S> findOne(Example<S> example) {
        return simpleJpaRepository.findOne(example);
    }

    @Override
    public <S extends Phone> List<S> findAll(Example<S> example) {
        return simpleJpaRepository.findAll(example);
    }

    @Override
    public <S extends Phone> List<S> findAll(Example<S> example, Sort sort) {
        return simpleJpaRepository.findAll(example, sort);
    }

    @Override
    public <S extends Phone> Page<S> findAll(Example<S> example, Pageable pageable) {
        return simpleJpaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Phone> long count(Example<S> example) {
        return simpleJpaRepository.count(example);
    }

    @Override
    public <S extends Phone> boolean exists(Example<S> example) {
        return simpleJpaRepository.exists(example);
    }

    @Override
    public <S extends Phone, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return simpleJpaRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Phone> S save(S entity) {
        return simpleJpaRepository.save(entity);
    }

    @Override
    public <S extends Phone> List<S> saveAll(Iterable<S> entities) {
        return simpleJpaRepository.saveAll(entities);
    }

    @Override
    public Optional<Phone> findById(Long aLong) {
        return simpleJpaRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return simpleJpaRepository.existsById(aLong);
    }

    @Override
    public List<Phone> findAll() {
        return simpleJpaRepository.findAll();
    }

    @Override
    public List<Phone> findAllById(Iterable<Long> longs) {
        return simpleJpaRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return simpleJpaRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        simpleJpaRepository.deleteById(aLong);
    }

    @Override
    public void delete(Phone entity) {
        simpleJpaRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        simpleJpaRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Phone> entities) {
        simpleJpaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        // TODO
        // select * from "users" where "id" = :id
        //  setParameter("id", 1);
        //  entityManager.createQuery("") uses JPQL

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Phone> criteriaQuery = criteriaBuilder.createCriteriaDelete(Phone.class);
        criteriaQuery.from(Phone.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }

    @Override
    public List<Phone> findAll(Sort sort) {
        return simpleJpaRepository.findAll(sort);
    }

    @Override
    public Page<Phone> findAll(Pageable pageable) {
        return simpleJpaRepository.findAll(pageable);
    }
}