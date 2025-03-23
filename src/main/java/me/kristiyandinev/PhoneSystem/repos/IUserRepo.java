package me.kristiyandinev.PhoneSystem.repos;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface IUserRepo extends JpaRepository<User, Integer> {

}
