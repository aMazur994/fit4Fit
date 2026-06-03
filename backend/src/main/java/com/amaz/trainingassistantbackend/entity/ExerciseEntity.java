package com.amaz.trainingassistantbackend.entity;

import com.amaz.trainingassistantbackend.utils.enums.BodyParts;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@FieldNameConstants
public class ExerciseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_part", nullable = false)
    private BodyParts bodyPart;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ExerciseEntity that = (ExerciseEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
