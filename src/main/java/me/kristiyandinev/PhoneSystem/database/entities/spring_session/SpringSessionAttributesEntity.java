package me.kristiyandinev.PhoneSystem.database.entities.spring_session;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpringSessionAttributesEntity implements Serializable {

    @EmbeddedId
    private SpringSessionAttributesId id;

    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
    private byte[] attributeBytes;

    @ManyToOne
    @MapsId("sessionPrimaryId")
    @JoinColumn(name = "SESSION_PRIMARY_ID", nullable = false)
    private SpringSessionEntity springSession;
}
