package com.quest.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.user.entity.TeamUserEntity;

public interface TeamUserRepository extends JpaRepository<TeamUserEntity, Long> {
	
	public TeamUserEntity findByHubName(String hubId);
	
	@Query("SELECT t FROM TeamUserEntity t WHERE t.hubName = :hubName AND t.status = 'OK' ORDER BY t.updDate DESC")
	TeamUserEntity findLatestTeamByHubName(@Param("hubName") String hubId);
}
