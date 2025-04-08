package me.kristiyandinev.PhoneSystem.database.repositories.spring_session;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.spring_session.SpringSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SpringSessionRepository extends JpaRepository<SpringSessionEntity, String> {
}
