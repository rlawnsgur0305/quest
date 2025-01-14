package com.quest.reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.reward.entity.TeamEntity;
import com.quest.reward.entity.UserEntity;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>{
	
}
