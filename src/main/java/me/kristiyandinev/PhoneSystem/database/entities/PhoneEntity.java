package me.kristiyandinev.PhoneSystem.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneEntity {

    @Column(nullable = false)
    public String number;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity userEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    public Long id;

    public PhoneEntity() {
        number = "";
        userEntity = null;
        id = null;
    }

    public PhoneEntity(String number, UserEntity userEntity, Long id) {
        this.number = number;
        this.userEntity = userEntity;
        this.id = id;
    }

    public PhoneEntity(PhoneEntity phoneEntity) {
        id = phoneEntity.id;
        number = phoneEntity.number;
        userEntity = phoneEntity.userEntity;
    }
}
