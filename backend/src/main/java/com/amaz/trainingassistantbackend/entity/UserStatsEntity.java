package com.amaz.trainingassistantbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="user_stats")
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@AllArgsConstructor
public class UserStatsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "id")
	private Long id;

	@Column(name = "height")
	private Long height ;

	@Column(name = "weight")
	private Long weight;

	@Column(name = "bmi")
	private String bmi;

	@CreatedDate
	@Column( name = "date_of_add")
	private LocalDateTime dateOfAdd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;
}
