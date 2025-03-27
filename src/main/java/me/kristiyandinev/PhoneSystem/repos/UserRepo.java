package me.kristiyandinev.PhoneSystem.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public abstract class UserRepo implements JpaRepository<User, Integer> {

    @Autowired
    private EntityManager entityManager;


    @Override
    public void deleteAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<User> criteriaQuery = criteriaBuilder.createCriteriaDelete(User.class);
        criteriaQuery.from(User.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }
}
