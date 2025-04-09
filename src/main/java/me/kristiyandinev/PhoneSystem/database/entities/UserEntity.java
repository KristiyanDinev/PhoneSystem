package me.kristiyandinev.PhoneSystem.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.kristiyandinev.PhoneSystem.dto.LoginUserDto;
import me.kristiyandinev.PhoneSystem.dto.RegisterUserDto;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime created_at;

    public UserEntity(RegisterUserDto registerUserDto) {
        id = null;
        name = registerUserDto.name;
        email = registerUserDto.email;
        password = registerUserDto.password;
        created_at = null;
    }

    public UserEntity(LoginUserDto loginUserDto) {
        id = null;
        name = "";
        email = loginUserDto.email;
        password = loginUserDto.password;
        created_at = null;
    }

    public UserEntity(UserEntity userEntity) {
        id = userEntity.id;
        name = userEntity.name;
        email = userEntity.email;
        password = userEntity.password;
        created_at = userEntity.created_at;
    }
}
