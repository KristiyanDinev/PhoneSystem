package me.kristiyandinev.PhoneSystem.database.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
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
public class PhoneRepository implements JpaRepository<PhoneEntity, Long> {

        private EntityManager entityManager;

        private SimpleJpaRepository<PhoneEntity, Long> simpleJpaRepository;

        @Autowired
        public PhoneRepository(EntityManager entityManager) {
            this.entityManager = entityManager;
            simpleJpaRepository = new SimpleJpaRepository<>(PhoneEntity.class, entityManager);
        }

        @Override
        public void flush() {
            simpleJpaRepository.flush();
        }

        @Override
        public <S extends PhoneEntity> S saveAndFlush(S entity) {
            return simpleJpaRepository.saveAndFlush(entity);
        }

        @Override
        public <S extends PhoneEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
            return simpleJpaRepository.saveAllAndFlush(entities);
        }

        @Override
        public void deleteAllInBatch(Iterable<PhoneEntity> entities) {
            simpleJpaRepository.deleteAllInBatch(entities);
        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> ints) {
            simpleJpaRepository.deleteAllByIdInBatch(ints);
        }

        @Override
        public void deleteAllInBatch() {
            simpleJpaRepository.deleteAllInBatch();
        }

        @Deprecated
        public PhoneEntity getOne(Long aInt) {
            return simpleJpaRepository.getOne(aInt);
        }

        @Override
        @Deprecated
        public PhoneEntity getById(Long aInt) {
            return simpleJpaRepository.getById(aInt);
        }

        @Override
        public PhoneEntity getReferenceById(Long aInt) {
            return simpleJpaRepository.getReferenceById(aInt);
        }

        @Override
        public <S extends PhoneEntity> Optional<S> findOne(Example<S> example) {
            return simpleJpaRepository.findOne(example);
        }

        @Override
        public <S extends PhoneEntity> List<S> findAll(Example<S> example) {
            return simpleJpaRepository.findAll(example);
        }

        @Override
        public <S extends PhoneEntity> List<S> findAll(Example<S> example, Sort sort) {
            return simpleJpaRepository.findAll(example, sort);
        }

        @Override
        public <S extends PhoneEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
            return simpleJpaRepository.findAll(example, pageable);
        }

        @Override
        public <S extends PhoneEntity> long count(Example<S> example) {
            return simpleJpaRepository.count(example);
        }

        @Override
        public <S extends PhoneEntity> boolean exists(Example<S> example) {
            return simpleJpaRepository.exists(example);
        }

        @Override
        public <S extends PhoneEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return simpleJpaRepository.findBy(example, queryFunction);
        }

        @Override
        public <S extends PhoneEntity> S save(S entity) {
            return simpleJpaRepository.save(entity);
        }

        @Override
        public <S extends PhoneEntity> List<S> saveAll(Iterable<S> entities) {
            return simpleJpaRepository.saveAll(entities);
        }

        @Override
        public Optional<PhoneEntity> findById(Long aInt) {
            return simpleJpaRepository.findById(aInt);
        }

        @Override
        public boolean existsById(Long aInt) {
            return simpleJpaRepository.existsById(aInt);
        }

        @Override
        public List<PhoneEntity> findAll() {
            return simpleJpaRepository.findAll();
        }

        @Override
        public List<PhoneEntity> findAllById(Iterable<Long> ints) {
            return simpleJpaRepository.findAllById(ints);
        }

        @Override
        public long count() {
            return simpleJpaRepository.count();
        }

        @Override
        public void deleteById(Long aInt) {
            simpleJpaRepository.deleteById(aInt);
        }

        @Override
        public void delete(PhoneEntity entity) {
            simpleJpaRepository.delete(entity);
        }

        @Override
        public void deleteAllById(Iterable<? extends Long> ints) {
            simpleJpaRepository.deleteAllById(ints);
        }

        @Override
        public void deleteAll(Iterable<? extends PhoneEntity> entities) {
            simpleJpaRepository.deleteAll(entities);
        }

        @Override
        public void deleteAll() {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaDelete<PhoneEntity> criteriaQuery = criteriaBuilder.createCriteriaDelete(PhoneEntity.class);
            criteriaQuery.from(PhoneEntity.class);

            entityManager.createQuery(criteriaQuery).executeUpdate();
        }

        @Override
        public List<PhoneEntity> findAll(Sort sort) {
            return simpleJpaRepository.findAll(sort);
        }

        @Override
        public Page<PhoneEntity> findAll(Pageable pageable) {
            return simpleJpaRepository.findAll(pageable);
        }


}