package me.kristiyandinev.PhoneSystem.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public abstract class PhoneRepo implements JpaRepository<Phone, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Phone> criteriaQuery = criteriaBuilder.createCriteriaDelete(Phone.class);
        criteriaQuery.from(Phone.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }
}