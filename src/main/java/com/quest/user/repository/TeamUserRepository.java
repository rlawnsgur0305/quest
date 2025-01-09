package com.quest.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.user.entity.TeamUserEntity;

public interface TeamUserRepository extends JpaRepository<TeamUserEntity, Long> {
	
	public TeamUserEntity findByHubName(String hubId);
}
