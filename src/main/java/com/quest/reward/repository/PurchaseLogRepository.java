package com.quest.reward.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.reward.entity.PurchaseLogEntity;

public interface PurchaseLogRepository extends JpaRepository<PurchaseLogEntity, Long> {
	
	List<PurchaseLogEntity> findByCrtDateBetween(LocalDate startDate , LocalDate endDate);
	
}
