package me.kristiyandinev.PhoneSystem.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public LocalDateTime created_at;

    public User() {
        id = null;
        name = "";
        email = "";
        password = "";
        created_at = null;
    }

    public User(Integer id, String name, String email, String password, LocalDateTime datetime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_at = datetime;
    }
}
