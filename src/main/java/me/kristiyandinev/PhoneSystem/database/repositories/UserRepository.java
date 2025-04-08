package me.kristiyandinev.PhoneSystem.database.repositories;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepositoryImplementation<UserEntity, Integer> {
}