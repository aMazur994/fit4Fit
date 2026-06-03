package com.amaz.trainingassistantbackend.entity;

import jakarta.persistence.*;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nazwa jest wymagana")
    @NotEmpty(message = "Nazwa nie może być pusta")
    @NotBlank(message = "Nazwa nie może być znakiem białym")
    private String name;

    @NotNull(message = "Hasło jest wymagane")
    @NotEmpty(message = "Hasło nie może być puste")
    @NotBlank(message = "Hasło nie może być znakiem białym")
    private String password;        // bcrypt

    @NotNull(message = "Email jest wymagany")
    @NotEmpty(message = "Email nie może być pusty")
    @Email(message = "Email musi być poprawny")
    private String email;

    @Lob
    private String base64Image;

    @CreatedDate
    private LocalDateTime dateOfAdd;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserStatsEntity> userStats = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TrainingEntity> trainingEntities = new LinkedHashSet<>();
}
