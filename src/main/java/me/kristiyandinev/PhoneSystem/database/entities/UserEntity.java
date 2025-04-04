package me.kristiyandinev.PhoneSystem.database.entities;

import jakarta.persistence.*;
import me.kristiyandinev.PhoneSystem.models.LoginUserModel;
import me.kristiyandinev.PhoneSystem.models.RegisterUserModel;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
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

    public UserEntity() {
        id = null;
        name = "";
        email = "";
        password = "";
        created_at = null;
    }

    public UserEntity(Integer id, String name, String email, String password, LocalDateTime datetime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = datetime;
    }

    public UserEntity(RegisterUserModel registerUserModel) {
        id = null;
        name = registerUserModel.name;
        email = registerUserModel.email;
        password = registerUserModel.password;
        created_at = null;
    }

    public UserEntity(LoginUserModel loginUserModel) {
        id = null;
        name = "";
        email = loginUserModel.email;
        password = loginUserModel.password;
        created_at = null;
    }
}
