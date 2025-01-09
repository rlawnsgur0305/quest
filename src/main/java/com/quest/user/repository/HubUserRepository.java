package com.quest.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest.user.entity.HubUserEntity;

@Repository
public interface HubUserRepository extends JpaRepository<HubUserEntity, Long> {
	
	List<HubUserEntity> findByHubId(String hubId);
	
}
