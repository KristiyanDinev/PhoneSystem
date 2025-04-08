package me.kristiyandinev.PhoneSystem.database.entities.spring_session;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SPRING_SESSION")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpringSessionEntity implements Serializable {

    @Id
    @Column(name = "PRIMARY_ID", length = 36, nullable = false)
    private String primaryId;

    @Column(name = "SESSION_ID", length = 36, nullable = false, unique = true)
    private String sessionId;

    @Column(name = "CREATION_TIME", nullable = false)
    private Long creationTime;

    @Column(name = "LAST_ACCESS_TIME", nullable = false)
    private Long lastAccessTime;

    @Column(name = "MAX_INACTIVE_INTERVAL", nullable = false)
    private Integer maxInactiveInterval;

    @Column(name = "EXPIRY_TIME", nullable = false)
    private Long expiryTime;

    @Column(name = "PRINCIPAL_NAME", length = 100)
    private String principalName;

    @OneToMany(mappedBy = "springSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpringSessionAttributesEntity> attributes = new HashSet<>();


    public void addAttribute(SpringSessionAttributesEntity attribute) {
        attributes.add(attribute);
        attribute.setSpringSession(this);
    }

    public void removeAttribute(SpringSessionAttributesEntity attribute) {
        attributes.remove(attribute);
        attribute.setSpringSession(null);
    }
}
