package me.kristiyandinev.PhoneSystem.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "phones")
public class Phone {
    @Column(nullable = false)
    public String number;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Phone() {
        number = "";
        user = null;
        id = null;
    }

    public Phone(String number, User user, Long id) {
        this.number = number;
        this.user = user;
        this.id = id;
    }
}
