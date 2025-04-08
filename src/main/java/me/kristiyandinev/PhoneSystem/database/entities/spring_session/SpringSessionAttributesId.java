package me.kristiyandinev.PhoneSystem.database.entities.spring_session;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;

@Embeddable
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpringSessionAttributesId {

    @Column(name = "SESSION_PRIMARY_ID", length = 36)
    private String sessionPrimaryId;

    @Column(name = "ATTRIBUTE_NAME", length = 200)
    private String attributeName;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSessionAttributesId that = (SpringSessionAttributesId) o;
        return Objects.equals(sessionPrimaryId, that.sessionPrimaryId) &&
                Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionPrimaryId, attributeName);
    }
}
