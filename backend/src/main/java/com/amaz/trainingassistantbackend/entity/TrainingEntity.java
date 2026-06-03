package com.amaz.trainingassistantbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "training")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Long id;

    @Column( name = "calories")
    private double calories;

    @Column( name = "trainingDate")
    private LocalDateTime trainingDate;

    @Column( name = "trainingDuration")
    private LocalTime trainingDuration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;


}
