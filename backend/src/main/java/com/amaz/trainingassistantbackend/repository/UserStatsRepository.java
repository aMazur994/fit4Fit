package com.amaz.trainingassistantbackend.repository;

import com.amaz.trainingassistantbackend.entity.UserStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatsRepository  extends JpaRepository<UserStatsEntity, Long> {

	@Query(value = "SELECT * FROM user_stats p where p.user_id = :accountId"+
			" ORDER BY p.date_of_add DESC LIMIT 1", nativeQuery = true)
	UserStatsEntity findLastStats(Long accountId);
}
