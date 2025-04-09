package me.kristiyandinev.PhoneSystem.database.repositories;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IPhoneRepository extends JpaRepositoryImplementation<PhoneEntity, Long> {
}