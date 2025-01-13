package com.quest.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quest.user.entity.TeamUserEntity;

public interface TeamUserRepository extends JpaRepository<TeamUserEntity, Long> {
	
	public TeamUserEntity findByHubName(String hubId);
	
	@Query("SELECT t FROM TeamUserEntity t WHERE t.hubName = :hubName ORDER BY t.crtDate DESC")
	List<TeamUserEntity> findLatestTeamByHubName(@Param("hubName") String hubId);

}
