package com.amaz.trainingassistantbackend.repository;

import com.amaz.trainingassistantbackend.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {

    @Query("SELECT e from ExerciseEntity e where e.name = :n")
    List<ExerciseEntity> getExerciseByName(String n);
}
