package com.amaz.trainingassistantbackend.repository;

import com.amaz.trainingassistantbackend.entity.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingRepository  extends JpaRepository<TrainingEntity, Long> {

	@Query("select t from TrainingEntity t " +
			"where t.user.id = :accountId " +
			"and t.trainingDate >= :dateFrom " +
			"and t.trainingDate<= :dateTo")
	List<TrainingEntity> getTrainingsByDatePeriod(Long accountId, LocalDateTime dateFrom, LocalDateTime dateTo);

	@Query("select t from TrainingEntity t " +
			"where t.user.id = :accountId ")
	List<TrainingEntity> getByAccountId(Long accountId);
}
